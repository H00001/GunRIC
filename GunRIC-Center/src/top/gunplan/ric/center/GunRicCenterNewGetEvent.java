package top.gunplan.ric.center;


import top.gunplan.ric.protocol.*;
import top.gunplan.ric.stand.GunRicGetAddressStand;
import top.gunplan.ric.stand.GunRicRetAddressStand;

import java.util.Set;

/**
 * @author dosdrtt
 * #date 2019/05/23
 */
public class GunRicCenterNewGetEvent implements GunRicCommonRealDeal<GunRicGetAddressStand, GunRicRetAddressStand> {
    private GunRicCenterRecordManager hinstance = GunRicCenterStdRecordManage.Instance.getHinstance();

    @Override
    public GunRicRetAddressStand dealDataEvent(GunRicGetAddressStand protocol) {
        BaseGunRicServerInformation g = new GunRicServerInformationImpl(protocol);
        Set<GunAddressItemInterface> s1 = hinstance.getFirstRecord().getAddress(g);
        return GunRicProtocolFactory.newGunRicRespAddressProtocol(s1);
    }
}
