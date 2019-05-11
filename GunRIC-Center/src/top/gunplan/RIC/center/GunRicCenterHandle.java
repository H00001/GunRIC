package top.gunplan.RIC.center;

import top.gunplan.ric.protocol.AbstractGunRicProtocol;
import top.gunplan.ric.protocol.GunRicGetAddressProcotol;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;

public class GunRicCenterHandle extends AbstractGunRicBaseCenterHandle {
    @Override
    public AbstractGunRicProtocol dealEvent(GunRicRegisterProtocol protocol) {
        return null;
    }

    @Override
    public AbstractGunRicProtocol dealEvent(GunRicGetAddressProcotol protocol) {
        return null;
    }
}
