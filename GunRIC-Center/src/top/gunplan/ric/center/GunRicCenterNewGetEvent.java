package top.gunplan.ric.center;

import top.gunplan.ric.protocol.*;

import java.util.List;

/**
 * @author dosdrtt
 * @date 2019/05/23
 */
public class GunRicCenterNewGetEvent implements GunRicCommonRealDeal<AbstractCenterHelperProtocol, GunRicRespAddressProtocol> {
    private GunRicCenterStdRecordManage hinstance = GunRicCenterStdRecordManage.Instance.getHinstance();

    @Override
    public GunRicRespAddressProtocol dealDataEvent(AbstractCenterHelperProtocol protocol) {
        BaseGunRicCdt g = new GunRicCdtImpl(protocol);
        List<GunAddressItemInterface> s1 = hinstance.getFirstRecord().getAddress(g);
        return GunRicProtocolFactory.newGunRicRespAddressProtocol(s1);
    }
}
