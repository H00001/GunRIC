package top.gunplan.ric.center;

import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.*;

/**
 * GunRicCenterHandle
 * @since init 4.1.5.6
 * @author dosdrtt
 */
public class GunRicCenterHandle extends AbstractGunRicBaseCenterHandle {
    private GunRicCommonRealDeal handle = new GunRicCenterNewRegisterEvent();
    private GunRicCommonRealDeal handle1 = new GunRicCenterNewGetEvent();
    @Override
    public GunNetOutputInterface dealEvent(GunRicRegisterProtocol protocol) {
        return dealMuchEvent(handle::dealDataEvent, protocol);
    }



    @Override
    public GunNetOutputInterface dealEvent(GunRicGetAddressProcotol protocol) {
        return dealMuchEvent(handle1::dealDataEvent, protocol);
    }


}