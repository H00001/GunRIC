package top.gunplan.RIC.center;

import top.gunplan.netty.protocol.GunNetInputInterface;

import java.net.InetSocketAddress;

/**
 * @author dosdrtt
 * @date 1557231535
 */
public final class GunRicCenterDto implements GunNetInputInterface {
    private final InetSocketAddress address;
    private final GunNetInputInterface obj;

    public GunRicCenterDto(InetSocketAddress address, GunNetInputInterface obj) {
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
