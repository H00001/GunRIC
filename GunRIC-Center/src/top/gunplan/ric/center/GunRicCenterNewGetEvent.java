package top.gunplan.ric.center;

import top.gunplan.ric.protocol.*;

import java.util.List;

/**
 * @author dosdrtt
 * @date 2019/05/23
 */
public class GunRicCenterNewGetEvent implements GunRicCommonRealDeal {
    private GunRicCenterStdRecordManage hinstance = GunRicCenterStdRecordManage.Instance.getHinstance();

    @Override
    public AbstractGunRicProtocol dealDataEvent(AbstractGunRicProtocol protocol) {
        GunRicInterfaceBuffer.GunRicCdtInterface gunRicCdtInterface = new GunRicInterfaceBuffer.GunRicCdtInterface((AbstractCenterHelperProtocol) protocol);
        List<GunAddressItem> addresses = hinstance.getFirstRecord().getAddress(gunRicCdtInterface);
        GunRicRespAddressProtocol ricRespAddressProtocol = new GunRicRespAddressProtocol();
        ricRespAddressProtocol.pushAddressList(addresses);
        return ricRespAddressProtocol;
    }
}
