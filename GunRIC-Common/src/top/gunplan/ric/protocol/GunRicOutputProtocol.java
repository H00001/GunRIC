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
        GunBytesUtil.GunWriteByteStream stream = createSpace();
        publicSet(stream);
        writeOnceParam(stream, returnValue.obj);
        stream.write(END_FLAG);
        return stream.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        int len = TYPE_LEN + SERIALIZE_LEN + CODE_LEN + 1 + END_FLAG.length;
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
