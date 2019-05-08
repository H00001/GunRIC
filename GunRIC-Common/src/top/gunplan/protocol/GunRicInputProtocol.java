package top.gunplan.protocol;

import top.gunplan.netty.GunException;
import top.gunplan.utils.AbstractGunBaseLogUtil;
import top.gunplan.utils.GunBytesUtil;

/**
 * no concurrent class
 *
 * @author dosdrtt
 * @date
 */
public final class GunRicInputProtocol extends AbstractGunRicExecuteProtocol {
    private ParamHelper[] helpers;

    private int paramlen = 0;
    private static final byte PARAM_LEN = 1;

    public ParamHelper[] getParaHelpers() {
        return helpers;
    }

    private boolean analyizeParams(int paramlen, GunBytesUtil.GunReadByteUtil util) {
        helpers = new ParamHelper[paramlen];
        for (int i = 0; i < paramlen; i++) {
            helpers[i] = readOnceParam(util);
        }
        return checkEnd(util);
    }

    public void setParamLen(int len) {
        paramlen = len;
        helpers = new ParamHelper[len];
    }


    private boolean writeParam(GunBytesUtil.GunWriteByteUtil util) {
        for (int i = 0; i < paramlen; i++) {
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
        int len = 2 + CODE_LEN + TYPE_LEN + PARAM_LEN + methodName.length() + interfaceName.length() + otherCount + END_FLAG.length;
        byte[] serizea = new byte[len];
        GunBytesUtil.GunWriteByteUtil serizUtil = new GunBytesUtil.GunWriteByteUtil(serizea);
        publicSet(serizUtil);
        super.stdHeadWrite(serizUtil);
        serizUtil.writeByte((byte) paramlen);
        if (!writeParam(serizUtil)) {
            throw new GunException("write Param error");
        }
        serizUtil.write(END_FLAG);
        return serizea;
    }


    public void pushParam(Object obj) {
        otherCount += 1;
        otherCount = addLenByParam(otherCount, obj);
        helpers[now++] = new ParamHelper(obj, RicProtocolParamType.valuefrom(obj.getClass()).clazz);

    }

    private int now;

    private int otherCount = 0;


    public byte getParamleng() {
        return (byte) paramlen;
    }


    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        publicUnSet(util);
        super.stdHeadAnaly(util);
        this.paramlen = util.readByte();
        return paramlen == 0 ? checkEnd(util) : analyizeParams(paramlen, util);
    }


    public Class<?>[] getParamTypeList() {
        Class<?>[] var2 = new Class<?>[paramlen];
        for (int i = 0; i < paramlen; i++) {
            var2[i] = helpers[i].clazz;
        }
        return var2;
    }

    public Object[] getParameters() {
        Object[] var2 = new Object[paramlen];
        for (int i = 0; i < paramlen; i++) {
            var2[i] = helpers[i].obj;
        }
        return var2;
    }
}
