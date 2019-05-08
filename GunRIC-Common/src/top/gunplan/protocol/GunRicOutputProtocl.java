package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 */
public class GunRicOutputProtocl extends AbstractGunRicExecuteProtocol {
    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        publicUnSet(util);
        returnValue = readOnceParam(util);
        return checkEnd(util);
    }

    private ParamHelper returnValue;

    public ParamHelper getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ParamHelper returnValue) {
        this.returnValue = returnValue;
    }


    @Override
    public byte[] serialize() {
        int len = TYPE_LEN + CODE_LEN + 1 + END_FLAGE.length;
        len = addLenByParam(len, returnValue.obj);
        byte[] serize = new byte[len];
        GunBytesUtil.GunWriteByteUtil serizUtil = new GunBytesUtil.GunWriteByteUtil(serize);
        publicSet(serizUtil);
        writeOnceParam(serizUtil, returnValue.obj);
        serizUtil.write(END_FLAGE);
        return serize;
    }
}
