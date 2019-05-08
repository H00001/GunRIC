package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 */
public class GunRicHelloProtocol extends AbstractGunRPCProtocl {
    GunRicHelloProtocol(boolean type) {
        this.type = RicProtoclType.HELLO;
        this.code = type ? RicProtoclCode.HELLO_REQ : RicProtoclCode.HELLO_RES;
    }

    GunRicHelloProtocol() {

    }

    @Override
    public boolean unSerialize(byte[] in) {
        return false;
    }

    @Override
    public byte[] serialize() {
        byte[] seriz = new byte[CODE_LEN + TYPE_LEN + END_FLAGE.length];
        GunBytesUtil.GunWriteByteUtil unserizutil = new GunBytesUtil.GunWriteByteUtil(seriz);
        publicSet(unserizutil);
        unserizutil.write(END_FLAGE);
        return seriz;
    }
}
