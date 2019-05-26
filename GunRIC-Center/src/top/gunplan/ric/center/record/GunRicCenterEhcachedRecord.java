package top.gunplan.ric.center.record;


import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.util.List;

/**
 * GunRicCenterEhcachedRecord
 *
 * @author dosdrtt
 * @date 2019/05/35
 * @see GunRicCenterRecord
 */
public class GunRicCenterEhcachedRecord extends AbstractGunRicProxyRecord {


    /**
     * proxy mode to find
     *
     * @param lastRecord find last
     */
    public GunRicCenterEhcachedRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
    }

    @Override
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunRicRespAddressProtocol.AddressItem address) {

    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunRicRespAddressProtocol.AddressItem address) {

    }


    @Override
    public List<GunRicRespAddressProtocol.AddressItem> getAddressBase(GunRicInterfaceBuffer.GunRicCdtInterface gInfo) {
        return null;
    }


}
