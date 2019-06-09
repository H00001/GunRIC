package top.gunplan.ric.center;

import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.*;

/**
 * @concurrent method
 * GunRicCenterHandle
 * @since init 4.1.5.6
 * @author dosdrtt
 */
public class GunRicCenterHandle extends AbstractGunRicBaseCenterHandle {
    private GunRicCommonRealDeal<GunRicRegisterProtocol, GunRicRegisterStatusProtocol> handle = new GunRicCenterNewRegisterEvent();
    private GunRicCommonRealDeal<AbstractCenterHelperProtocol, GunRicRespAddressProtocol> handle1 = new GunRicCenterNewGetEvent();
    @Override
    public GunNetOutputInterface dealEvent(GunRicRegisterProtocol protocol) {
        return dealMuchEvent(handle::dealDataEvent, protocol);
    }



    @Override
    public GunNetOutputInterface dealEvent(GunRicGetAddressProtocol protocol) {
        return dealMuchEvent(handle1::dealDataEvent, protocol);
    }


}