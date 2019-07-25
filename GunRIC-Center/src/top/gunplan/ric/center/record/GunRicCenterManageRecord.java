package top.gunplan.ric.center.record;

import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Set;

public class GunRicCenterManageRecord extends AbstractGunRicProxyRecord {
    GunRicCenterManageRecord(AbstractGunRicProxyRecord lastRecord) {
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
    Set<GunAddressItemInterface> getAddressBase(BaseGunRicServerInformation g) {
        return null;
    }
}
