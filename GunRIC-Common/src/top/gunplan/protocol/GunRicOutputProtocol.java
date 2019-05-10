package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * GunRicOutputProtocol
 *
 * @author dosdrtt
 */
public class GunRicOutputProtocol extends AbstractGunRicExecuteProtocol implements GunRicOutputHelper {
    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        publicUnSet(util);
        returnValue = readOnceParam(util);
        return checkEnd(util) && checKNext(in, util);
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
        GunBytesUtil.GunWriteByteUtil serizUtil = createSpace();
        publicSet(serizUtil);
        writeOnceParam(serizUtil, returnValue.obj);
        serizUtil.write(END_FLAG);
        return serizUtil.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteUtil createSpace() {
        int len = TYPE_LEN + SERIALNUM_LEN + CODE_LEN + 1 + END_FLAG.length;
        len = addLenByParam(len, returnValue.obj);
        byte[] serize = new byte[len];
        return new GunBytesUtil.GunWriteByteUtil(serize);
    }
}
