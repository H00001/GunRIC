package top.gunplan.ric.center.record;


import top.gunplan.ric.center.GunRicCdtInterface;
import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.protocol.GunAddressItem;

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
    public void firstAdd(GunRicCdtInterface g, GunAddressItem address) {

    }

    @Override
    public void nextAdd(GunRicCdtInterface g, GunAddressItem address) {

    }


    @Override
    public List<GunAddressItem> getAddressBase(GunRicCdtInterface gInfo) {
        return null;
    }


}
