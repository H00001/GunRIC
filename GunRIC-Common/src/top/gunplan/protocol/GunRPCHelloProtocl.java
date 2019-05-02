package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

public class GunRPCHelloProtocl extends AbstractGunRPCProtocl {
    GunRPCHelloProtocl(boolean type) {
        this.type = type ? RPCProtoclType.PING : RPCProtoclType.PONG;
    }


    @Override
    public boolean unSerialize(byte[] in) {
        return false;
    }

    @Override
    public byte[] serialize() {
        byte[] seriz = new byte[4];
        GunBytesUtil.GunWriteByteUtil unserizutil = new GunBytesUtil.GunWriteByteUtil(seriz);
        unserizutil.write(this.type.value);
        unserizutil.write(endFlage);
        return seriz;
    }
}
