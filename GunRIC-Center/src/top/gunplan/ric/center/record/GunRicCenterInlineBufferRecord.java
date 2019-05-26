package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

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
public class GunRicCenterInlineBufferRecord extends AbstractGunRicProxyRecord {


    public GunRicCenterInlineBufferRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
    }

    @Override
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunRicRespAddressProtocol.AddressItem address) {
        writeBufferAddress(g, address, true);
    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunRicRespAddressProtocol.AddressItem address) {
        writeBufferAddress(g, address, false);
    }

    @Override
    List<GunRicRespAddressProtocol.AddressItem> getAddressBase(GunRicInterfaceBuffer.GunRicCdtInterface g) {
        return GunRicInterfaceBuffer.intermapping.get(g);
    }


    private void writeBufferAddress(GunRicInterfaceBuffer.GunRicCdtInterface g, final GunRicRespAddressProtocol.AddressItem address, boolean firstWrite) {
        if (firstWrite) {
            List<GunRicRespAddressProtocol.AddressItem> adds = new ArrayList<>();
            adds.add(address);
            GunRicInterfaceBuffer.intermapping.put(g, adds);
        } else {
            GunRicInterfaceBuffer.intermapping.get(g).add(address);
        }
    }
}
