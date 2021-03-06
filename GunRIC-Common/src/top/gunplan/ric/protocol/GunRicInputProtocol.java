package top.gunplan.ric.protocol;

import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.ric.protocol.exp.GunRicProtocolException;
import top.gunplan.ric.stand.GunRicInvokeReqStand;
import top.gunplan.utils.GunBytesUtil;

import static top.gunplan.ric.protocol.exp.GunRicProtocolException.GunRicProtocolErrorType.UNKNOWN;
import static top.gunplan.ric.protocol.exp.GunRicProtocolException.GunRicProtocolErrorType.WRITE_PARAM_ERROR;

/**
 * no concurrent class
 *
 * @author dosdrtt
 * #date
 */
public final class GunRicInputProtocol extends AbstractGunRicExecuteProtocol implements GunRicOutputHelper, GunRicInvokeReqStand {
    private ParamHelper[] helpers;

    private RicProtocolParamType returnType;

    public GunRicInputProtocol() {
        this.setType(RicProtocolType.REQUEST);
        this.setCode(RicProtocolCode.SUCCEED);
    }

    private int paramLen = 0;
    private static final byte PARAM_LEN = 1;

    public ParamHelper[] getParaHelpers() {
        return helpers;
    }

    private boolean analyzeParams(int paramLen, GunBytesUtil.GunReadByteStream util) {
        helpers = new ParamHelper[paramLen];
        for (int i = 0; i < paramLen; i++) {
            helpers[i] = readOnceParam(util);
        }
        return checkEnd(util);
    }

    private void setParamLen(int len) {
        paramLen = len;
        helpers = new ParamHelper[len];
    }

    @Override
    public void pushParams(Object[] args) throws GunRicProtocolException {
        setParamLen((byte) args.length);
        for (Object arg : args) {
            if (!pushParam(arg)) {
                throw new GunRicProtocolException("push parameters error", UNKNOWN);

            }
        }
    }

    private boolean writeParam(GunBytesUtil.GunWriteByteStream util) {
        for (int i = 0; i < paramLen; i++) {
            Object fil = helpers[i].obj;
            try {
                writeOnceParam(util, fil);
            } catch (Exception exp) {
                GunNettyContext.logger.error(exp);
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
            throw new GunRicProtocolException("write Params error", WRITE_PARAM_ERROR);
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


    @Override
    public int paramLength() {
        return paramLen;
    }


    @Override
    public Class<?>[] paramTypes() {
        Class<?>[] var2 = new Class<?>[paramLen];
        for (int i = 0; i < paramLen; i++) {
            var2[i] = helpers[i].clazz;
        }
        return var2;
    }

    @Override
    public Class<?> returnType() {
        return returnType.clazz;
    }

    @Override
    public Object[] parameters() {
        Object[] var2 = new Object[paramLen];
        for (int i = 0; i < paramLen; i++) {
            var2[i] = helpers[i].obj;
        }
        return var2;
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        int len = METHOD_LEN_AND_INTERFACE_LEN + STAND_BASE_MIN_LEN + PARAM_LEN + methodName.length() + interfaceName.length() + otherCount;
        return new GunBytesUtil.GunWriteByteStream(new byte[len]);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        super.stdHeadAnaly(util);
        this.paramLen = util.readByte();
        boolean b = paramLen == 0 ? checkEnd(util) : analyzeParams(paramLen, util);
        return b && checkNext(util);
    }
}
