package top.gunplan.ric.center.record;

import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.List;

public class GunRicCenterManageRecord extends AbstractGunRicProxyRecord {
    GunRicCenterManageRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
    }

    @Override
    public void firstAdd(BaseGunRicCdt g, GunAddressItemInterface address) {

    }

    @Override
    public void nextAdd(BaseGunRicCdt g, GunAddressItemInterface address) {

    }

    @Override
    List<GunAddressItemInterface> getAddressBase(BaseGunRicCdt g) {
        return null;
    }
}
