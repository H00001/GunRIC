package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.GunRicInterfaceBuffer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * java inline record
 *
 * @author dosdrtt
 * @date 1558736830
 * @see GunRicCenterRecord
 */
public class GunRicCenterInlineBufferRecord implements GunRicCenterRecord {


    @Override
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) {
        writeBufferAddress(g, address, true);
    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) {
        writeBufferAddress(g, address, false);
    }

    private void writeBufferAddress(GunRicInterfaceBuffer.GunRicCdtInterface g, final InetSocketAddress address, boolean firstWrite) {
        if (firstWrite) {
            List<InetSocketAddress> adds = new ArrayList<>();
            adds.add(address);
            GunRicInterfaceBuffer.intermapping.put(g, adds);
        } else {
            GunRicInterfaceBuffer.intermapping.get(g).add(address);
        }
    }
}
