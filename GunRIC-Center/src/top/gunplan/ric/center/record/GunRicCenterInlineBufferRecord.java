package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.common.GunRicCommonBuffered;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;

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

    private GunRicCommonBuffered<BaseGunRicCdt> buffered = GunRicInterfaceBuffer.newInstance();

    public GunRicCenterInlineBufferRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
    }

    @Override
    public void firstAdd(BaseGunRicCdt g, GunAddressItemInterface address) {
        writeBufferAddress(g, address, true);
    }

    @Override
    public void nextAdd(BaseGunRicCdt g, GunAddressItemInterface address) {
        writeBufferAddress(g, address, false);
    }

    @Override
    List<GunAddressItemInterface> getAddressBase(BaseGunRicCdt g) {
        return buffered.get(g);
    }


    private void writeBufferAddress(BaseGunRicCdt g, final GunAddressItemInterface address, boolean firstWrite) {
        if (firstWrite) {
            List<GunAddressItemInterface> adds = new ArrayList<>();
            adds.add(address);
            buffered.push(g, adds);

        } else {
            buffered.get(g).add(address);
        }
    }
}
