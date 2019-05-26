package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * GunRicOutputProtocol
 *
 * @author dosdrtt
 */
public class GunRicOutputProtocol extends AbstractGunRicExecuteProtocol implements GunRicOutputHelper {

    public GunRicOutputProtocol() {
        this.type = RicProtocolType.RESPONSE;
        this.code = RicProtocolCode.SUCCEED;
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
        GunBytesUtil.GunWriteByteStream serizUtil = createSpace();
        publicSet(serizUtil);
        writeOnceParam(serizUtil, returnValue.obj);
        serizUtil.write(END_FLAG);
        return serizUtil.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        int len = TYPE_LEN + SERIALNUM_LEN + CODE_LEN + 1 + END_FLAG.length;
        len = addLenByParam(len, returnValue.obj);
        byte[] serize = new byte[len];
        return new GunBytesUtil.GunWriteByteStream(serize);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        returnValue = readOnceParam(util);
        return checkEnd(util) && checkNext(util);
    }
}
