package top.gunplan.ric.protocol;

import top.gunplan.netty.protocol.GunNetOutputInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dosdrtt
 */
public final class GunCombineOutput implements GunNetOutputInterface {
    private List<GunNetOutputInterface> protocollist = new ArrayList<>(1);

    public void push(GunNetOutputInterface protocol) {
        protocollist.add(protocol);
    }

    @Override
    public byte[] serialize() {
        int len = 0;
        int listlen = protocollist.size();
        byte[][] lists = new byte[listlen][];
        for (int i = 0; i < listlen; i++) {
            byte[] seri = protocollist.get(i).serialize();
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
