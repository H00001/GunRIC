package top.gunplan.ric.provider;


import top.gunplan.ric.protocol.GunRicCommonRealDeal;
import top.gunplan.ric.stand.GunRicInvokeReqStand;
import top.gunplan.ric.stand.GunRicInvokeResStand;


/**
 * @author dosdrtt
 * @concurrent
 * #date 1518000000
 */
public class GunRicProvideHandle extends AbstractGunRicBaseProviderHandle {


    private GunRicCommonRealDeal<GunRicInvokeReqStand, GunRicInvokeResStand> handle = new GunStdRicProviderHandle();

    @Override
    public GunRicInvokeResStand dealEvent(GunRicInvokeReqStand protocol) {
        return dealMuchEvent(handle::dealDataEvent, protocol);
    }


}
