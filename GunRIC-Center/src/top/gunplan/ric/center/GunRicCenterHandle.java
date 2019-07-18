package top.gunplan.ric.center;

import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.stand.GunRicGetAddressStand;
import top.gunplan.ric.stand.GunRicRegisterStand;
import top.gunplan.ric.stand.GunRicRegisterStateStand;
import top.gunplan.ric.stand.GunRicRetAddressStand;

/**
 * @author dosdrtt
 * @concurrent method
 * GunRicCenterHandle
 * @since init 4.1.5.7
 */
public class GunRicCenterHandle extends AbstractGunRicBaseCenterHandle {
    private GunRicCommonRealDeal<GunRicRegisterStand, GunRicRegisterStateStand> handle = new GunRicCenterNewRegisterEvent();
    private GunRicCommonRealDeal<GunRicGetAddressStand, GunRicRetAddressStand> handle1 = new GunRicCenterNewGetEvent();

    @Override
    public GunNetOutputInterface dealEvent(GunRicRegisterStand protocol) {
        return dealMuchEvent(handle::dealDataEvent, protocol);
    }


    @Override
    public GunNetOutputInterface dealEvent(GunRicGetAddressStand protocol) {
        return dealMuchEvent(handle1::dealDataEvent, protocol);
    }


}