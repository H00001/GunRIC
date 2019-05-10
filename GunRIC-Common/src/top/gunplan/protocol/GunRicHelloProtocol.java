package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 */
public class GunRicHelloProtocol extends AbstractGunRicProtocol implements GunRicOutputHelper {
    GunRicHelloProtocol(boolean type) {
        this.type = RicProtocolType.HELLO;
        this.code = type ? RicProtocolCode.HELLO_REQ : RicProtocolCode.HELLO_RES;
    }

    GunRicHelloProtocol() {

    }

    @Override
    public boolean unSerialize(byte[] in) {
        return false;
    }

    @Override
    public byte[] serialize() {
        GunBytesUtil.GunWriteByteUtil util = createSpace();
        publicSet(util);
        util.write(END_FLAG);
        return util.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteUtil createSpace() {
        byte[] seriz = new byte[CODE_LEN + SERIALNUM_LEN + TYPE_LEN + END_FLAG.length];
        return new GunBytesUtil.GunWriteByteUtil(seriz);
    }
}
