package top.gunplan.ric.center.record;


import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Set;

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
    public void firstAdd(BaseGunRicServerInformation g, GunAddressItemInterface address) {

    }

    @Override
    public void nextAdd(BaseGunRicServerInformation g, GunAddressItemInterface address) {

    }

    @Override
    public void remove(GunAddressItemInterface address) {

    }


    @Override
    public Set<GunAddressItemInterface> getAddressBase(BaseGunRicServerInformation gInfo) {
        return null;
    }


}
