package top.gunplan.ric.center;


import top.gunplan.ric.center.common.protocol.GunRICClusterInformation;
import top.gunplan.ric.center.common.protocol.GunRICClusterSynchroizedInformation;
import top.gunplan.ric.protocol.GunRicCommonRealDeal;
import top.gunplan.ric.stand.GunRicGetAddressStand;
import top.gunplan.ric.stand.GunRicRegisterStand;
import top.gunplan.ric.stand.GunRicRegisterStateStand;
import top.gunplan.ric.stand.GunRicRetAddressStand;

import java.net.SocketAddress;


/**
 * @author dosdrtt
 * @concurrent method
 * GunRicCenterHandle
 * @since init 4.1.5.8
 */
public class GunRicCenterHandle extends AbstractGunRicBaseCenterHandle {
    private GunRicCommonRealDeal<GunRicRegisterStand, GunRicRegisterStateStand> handle = new GunRicCenterNewRegisterEvent();
    private GunRicCommonRealDeal<GunRicGetAddressStand, GunRicRetAddressStand> handle1 = new GunRicCenterNewGetEvent();
    private GunRicCommonRealDeal<GunRICClusterInformation, GunRICClusterSynchroizedInformation> handle2 = new GunRicCenterClusterSyncEvent();

    @Override
    public GunRicRegisterStateStand dealEvent(GunRicRegisterStand protocol) {
        return dealMuchEvent(handle::dealDataEvent, protocol);
    }


    @Override
    public GunRicRetAddressStand dealEvent(GunRicGetAddressStand protocol) {
        return dealMuchEvent(handle1::dealDataEvent, protocol);
    }

    @Override
    public GunRICClusterSynchroizedInformation dealEvent(GunRICClusterInformation protocol) {
        return dealMuchEvent(handle2::dealDataEvent, protocol);
    }


    @Override
    public void dealCloseEvent(SocketAddress socketAddress) {

    }
}