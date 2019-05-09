package top.gunplan.RIC.center;

import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author dosdrtt
 * @date 1557231535
 */
public final class GunRicCenterDto implements GunNetInputInterface, GunNetOutputInterface {
    private final InetSocketAddress address;
    private final List<GunNetInputInterface> obji;
    private GunNetOutputInterface[] objo;

    public GunRicCenterDto(InetSocketAddress address, List<GunNetInputInterface> obj) {
        this.address = address;
        this.obji = obj;
    }

    public GunNetOutputInterface[] getObjo() {
        return objo;
    }

    void setObjo(GunNetOutputInterface[] objo) {
        this.objo = objo;
    }

    List<GunNetInputInterface> getObji() {
        return obji;
    }

    InetSocketAddress getAddress() {
        return address;
    }

    @Override
    public boolean unSerialize(byte[] bytes) {
        return false;
    }

    @Override
    public byte[] serialize() {
        int len = 0;
        int listlen = objo.length;
        byte[][] lists = new byte[listlen][];
        for (int i = 0; i < listlen; i++) {
            byte[] seri = objo[i].serialize();
            lists[i] = seri;
            len += seri.length;
        }
        byte[] sv = new byte[len];
        int now = 0;
        for (int i = 0; i < listlen; i++) {
            System.arraycopy(lists[i], 0, sv, now, lists[i].length);
            now += lists[i].length;
        }
        return sv;
    }
}
