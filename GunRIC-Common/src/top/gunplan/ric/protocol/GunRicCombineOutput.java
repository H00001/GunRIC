package top.gunplan.ric.protocol;


import top.gunplan.netty.protocol.GunNetOutBound;
import top.gunplan.ric.stand.GunRicBaseStand;
import top.gunplan.utils.GunBytesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dosdrtt
 */
public final class GunRicCombineOutput extends AbstractGunRicProtocol implements GunRicBaseStand {
    private List<GunNetOutBound> protocolList = new ArrayList<>(1);

    public void push(GunNetOutBound protocol) {
        protocolList.add(protocol);
    }

    @Override
    public byte[] serialize() {
        int len = 0;
        int listlen = protocolList.size();
        byte[][] lists = new byte[listlen][];
        for (int i = 0; i < listlen; i++) {
            byte[] seri = protocolList.get(i).serialize();
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

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        return false;
    }
}
