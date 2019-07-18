package top.gunplan.ric.protocol;

import top.gunplan.ric.stand.GunRicInvokeReqStand;
import top.gunplan.ric.stand.GunRicInvokeResStand;
import top.gunplan.utils.GunBytesUtil;

/**
 * GunRicOutputProtocol
 *
 * @author dosdrtt
 */
public class GunRicOutputProtocol extends AbstractGunRicExecuteProtocol implements GunRicOutputHelper, GunRicInvokeResStand {

    GunRicOutputProtocol() {
        this.type = RicProtocolType.RESPONSE;
        this.code = RicProtocolCode.SUCCEED;
    }

    public GunRicOutputProtocol(GunRicInvokeReqStand request) {
        this();
        setSerialnumber(request.serialNumber());
    }

    private ParamHelper returnValue;

    @Override
    public ParamHelper result() {
        return returnValue;
    }

    @Override
    public void setResult(ParamHelper returnValue) {
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
        byte[] serial = new byte[len];
        return new GunBytesUtil.GunWriteByteStream(serial);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        returnValue = readOnceParam(util);
        return checkEnd(util) && checkNext(util);
    }
}
