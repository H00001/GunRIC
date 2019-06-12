package top.gunplan.ric.protocol;

import top.gunplan.ric.protocol.exp.GunRicProtocolError;
import top.gunplan.utils.AbstractGunBaseLogUtil;
import top.gunplan.utils.GunBytesUtil;

import static top.gunplan.ric.protocol.exp.GunRicProtocolError.GunRicProtocolErrorType.WRITE_PARAM_ERROR;

/**
 * no concurrent class
 *
 * @author dosdrtt
 * @date
 */
public final class GunRicInputProtocol extends AbstractGunRicExecuteProtocol implements GunRicOutputHelper {
    private ParamHelper[] helpers;

    public GunRicInputProtocol() {
        this.setType(RicProtocolType.REQUEST);
        this.setCode(RicProtocolCode.SUCCEED);
    }

    private int paramLen = 0;
    private static final byte PARAM_LEN = 1;

    public ParamHelper[] getParaHelpers() {
        return helpers;
    }

    private boolean analyizeParams(int paramlen, GunBytesUtil.GunReadByteStream util) {
        helpers = new ParamHelper[paramlen];
        for (int i = 0; i < paramlen; i++) {
            helpers[i] = readOnceParam(util);
        }
        return checkEnd(util);
    }

    private void setParamLen(int len) {
        paramLen = len;
        helpers = new ParamHelper[len];
    }

    public void pushParams(Object[] args) {
        setParamLen((byte) args.length);
        for (Object arg : args) {
            if (!pushParam(arg)) {
                AbstractGunBaseLogUtil.error("push parameters error");
                return;
            }
        }
    }

    private boolean writeParam(GunBytesUtil.GunWriteByteStream util) {
        for (int i = 0; i < paramLen; i++) {
            Object fil = helpers[i].obj;
            try {
                writeOnceParam(util, fil);
            } catch (Exception exp) {
                AbstractGunBaseLogUtil.error(exp);
                return false;
            }
        }
        return true;
    }


    @Override
    public byte[] serialize() {
        GunBytesUtil.GunWriteByteStream util = createSpace();
        publicSet(util);
        stdHeadWrite(util);
        util.writeByte((byte) paramLen);
        if (!writeParam(util)) {
            throw new GunRicProtocolError("write Params error", WRITE_PARAM_ERROR);
        }
        util.write(END_FLAG);
        return util.getInput();
    }


    private boolean pushParam(Object obj) {
        otherCount += 1;
        try {
            otherCount = addLenByParam(otherCount, obj);
        } catch (Exception exp) {
            return false;
        }
        helpers[now++] = new ParamHelper(obj, RicProtocolParamType.valuefrom(obj.getClass()).clazz);
        return true;
    }

    private int now;

    private int otherCount = 0;


    public byte getParamLeng() {
        return (byte) paramLen;
    }


    public Class<?>[] getParamTypeList() {
        Class<?>[] var2 = new Class<?>[paramLen];
        for (int i = 0; i < paramLen; i++) {
            var2[i] = helpers[i].clazz;
        }
        return var2;
    }

    public Object[] getParameters() {
        Object[] var2 = new Object[paramLen];
        for (int i = 0; i < paramLen; i++) {
            var2[i] = helpers[i].obj;
        }
        return var2;
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        int len = 2 + SERIALIZE_LEN + CODE_LEN + TYPE_LEN + PARAM_LEN + methodName.length() + interfaceName.length() + otherCount + END_FLAG.length;
        return new GunBytesUtil.GunWriteByteStream(new byte[len]);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        super.stdHeadAnaly(util);
        this.paramLen = util.readByte();
        boolean b = paramLen == 0 ? checkEnd(util) : analyizeParams(paramLen, util);
        return b && checkNext(util);
    }
}
