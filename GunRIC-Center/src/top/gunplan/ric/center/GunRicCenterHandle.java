package top.gunplan.ric.center;

import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.stand.*;

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
    public GunRicRegisterStateStand dealEvent(GunRicRegisterStand protocol) {
        return dealMuchEvent(handle::dealDataEvent, protocol);
    }


    @Override
    public GunRicRetAddressStand dealEvent(GunRicGetAddressStand protocol) {
        return dealMuchEvent(handle1::dealDataEvent, protocol);
    }


}