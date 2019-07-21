package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.common.GunRicCommonBuffered;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * java inline record
 *
 * @author dosdrtt
 * @date 1558736830
 * @see GunRicCenterRecord
 */
public class GunRicCenterInlineBufferRecord extends AbstractGunRicProxyRecord {

    private final GunRicCommonBuffered<BaseGunRicCdt> buffered = GunRicInterfaceBuffer.newInstance();

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
    public void remove(GunAddressItemInterface address) {
        buffered.remove(address);
    }

    @Override
    Set<GunAddressItemInterface> getAddressBase(BaseGunRicCdt g) {
        return buffered.get(g);
    }


    private void writeBufferAddress(BaseGunRicCdt g, final GunAddressItemInterface address, boolean firstWrite) {
        if (firstWrite) {
            try {
                HashSet<GunAddressItemInterface> adds = new LinkedHashSet<>(16);
                adds.add(address);
                buffered.push(g, adds);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        } else {
            buffered.get(g).add(address);
        }
    }


}
