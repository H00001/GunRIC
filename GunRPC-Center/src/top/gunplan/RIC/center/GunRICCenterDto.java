package top.gunplan.RIC.center;

import top.gunplan.netty.protocol.GunNetInputInterface;

import java.net.InetSocketAddress;

public final class GunRICCenterDto implements GunNetInputInterface {
    private final InetSocketAddress address;
    private final GunNetInputInterface obj;

    public GunRICCenterDto(InetSocketAddress address, GunNetInputInterface obj) {
        this.address = address;
        this.obj = obj;
    }

    public GunNetInputInterface getObj() {
        return obj;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    @Override
    public boolean unSerialize(byte[] bytes) {
        return obj.unSerialize(bytes);
    }
}
