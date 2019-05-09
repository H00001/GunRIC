package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 */
public class GunRicHelloProtocol extends AbstractGunRicProtocol {
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
        byte[] seriz = new byte[CODE_LEN + TYPE_LEN + END_FLAG.length];
        GunBytesUtil.GunWriteByteUtil util = new GunBytesUtil.GunWriteByteUtil(seriz);
        publicSet(util);
        util.write(END_FLAG);
        return seriz;
    }
}
