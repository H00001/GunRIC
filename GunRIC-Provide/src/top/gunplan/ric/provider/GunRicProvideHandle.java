package top.gunplan.ric.provider;


import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.GunRicCommonRealDeal;
import top.gunplan.ric.protocol.GunRicInputProtocol;
import top.gunplan.ric.protocol.GunRicOutputProtocol;


/**
 * @author dosdrtt
 * @concurrent
 * @date 1518000000
 */
public class GunRicProvideHandle extends AbstractGunRicBaseProviderHandle {


    private GunRicCommonRealDeal<GunRicInputProtocol, GunRicOutputProtocol> handle = new GunStdRicProviderHandle();

    @Override
    public GunNetOutputInterface dealEvent(GunRicInputProtocol protocol) {
        return dealMuchEvent(handle::dealDataEvent, protocol);
    }

}
