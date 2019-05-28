package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCdtInterface;
import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.common.GunRicCommonBuffered;
import top.gunplan.ric.protocol.GunAddressItem;

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

    private GunRicCommonBuffered<GunRicCdtInterface> buffered = GunRicInterfaceBuffer.newInstance();

    public GunRicCenterInlineBufferRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
    }

    @Override
    public void firstAdd(GunRicCdtInterface g, GunAddressItem address) {
        writeBufferAddress(g, address, true);
    }

    @Override
    public void nextAdd(GunRicCdtInterface g, GunAddressItem address) {
        writeBufferAddress(g, address, false);
    }

    @Override
    List<GunAddressItem> getAddressBase(GunRicCdtInterface g) {
        return buffered.get(g);
    }


    private void writeBufferAddress(GunRicCdtInterface g, final GunAddressItem address, boolean firstWrite) {
        if (firstWrite) {
            List<GunAddressItem> adds = new ArrayList<>();
            adds.add(address);
            buffered.push(g, adds);

        } else {
            buffered.get(g).add(address);
        }
    }
}
