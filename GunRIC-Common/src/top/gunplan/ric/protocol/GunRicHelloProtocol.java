package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 */
public class GunRicHelloProtocol extends AbstractGunRicProtocol implements GunRicOutputHelper,GunRicProtocolBody {
    public GunRicHelloProtocol(boolean type) {
        this.type = RicProtocolType.HELLO;
        this.code = type ? RicProtocolCode.HELLO_REQ : RicProtocolCode.HELLO_RES;
    }

    public GunRicHelloProtocol() {
        super();
    }

    @Override
    public byte[] serialize() {
        GunBytesUtil.GunWriteByteStream util = createSpace();
        publicSet(util);
        util.write(END_FLAG);
        return util.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        return new GunBytesUtil.GunWriteByteStream(new byte[CODE_LEN + SERIALIZE_LEN + TYPE_LEN + END_FLAG.length]);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        return checkEnd(util) && checkNext(util);
    }

    @Override
    public boolean fillData(GunBytesUtil.GunWriteByteStream stream) {
        return true;
    }

    @Override
    public int needSpace() {
        return 0;
    }
}
