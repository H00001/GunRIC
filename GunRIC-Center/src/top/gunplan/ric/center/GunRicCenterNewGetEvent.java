package top.gunplan.ric.center;

import top.gunplan.ric.protocol.*;
import top.gunplan.ric.stand.GunRicGetAddressStand;
import top.gunplan.ric.stand.GunRicRetAddressStand;

import java.util.List;

/**
 * @author dosdrtt
 * @date 2019/05/23
 */
public class GunRicCenterNewGetEvent implements GunRicCommonRealDeal<GunRicGetAddressStand, GunRicRetAddressStand> {
    private GunRicCenterStdRecordManage hinstance = GunRicCenterStdRecordManage.Instance.getHinstance();

    @Override
    public GunRicRetAddressStand dealDataEvent(GunRicGetAddressStand protocol) {
        BaseGunRicCdt g = new GunRicCdtImpl(protocol);
        List<GunAddressItemInterface> s1 = hinstance.getFirstRecord().getAddress(g);
        return GunRicProtocolFactory.newGunRicRespAddressProtocol(s1);
    }
}
