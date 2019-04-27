package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 */
public class GunRPCOutputProtocl extends AbstractGunRPCExecuteProtocol {
    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        publicUnSet(util);
        returnValue = readOnceParam(util);
        return checkEnd(util);
    }

    private Object returnValue;

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }


    @Override
    public byte[] serialize() {
        int len = 6 + 1;
        len = addLenByParam(len, returnValue);
        byte[] serize = new byte[len];
        GunBytesUtil.GunWriteByteUtil serizUtil = new GunBytesUtil.GunWriteByteUtil(serize);
        publicSet(serizUtil);
        writeOnceParam(serizUtil, returnValue);
        serizUtil.write(endFlage);
        return serize;
    }
}
