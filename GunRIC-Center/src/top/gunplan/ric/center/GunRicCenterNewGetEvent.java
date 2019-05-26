package top.gunplan.ric.center;

import top.gunplan.ric.protocol.AbstractCenterHelperProtocol;
import top.gunplan.ric.protocol.AbstractGunRicProtocol;
import top.gunplan.ric.protocol.GunRicCommonRealDeal;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;
import java.util.List;

/**
 * @author dosdrtt
 * @date 2019/05/23
 */
public class GunRicCenterNewGetEvent implements GunRicCommonRealDeal {
    @Override
    public AbstractGunRicProtocol dealDataEvent(AbstractGunRicProtocol protocol) {
        GunRicInterfaceBuffer.GunRicCdtInterface gunRicCdtInterface = new GunRicInterfaceBuffer.GunRicCdtInterface((AbstractCenterHelperProtocol) protocol);
        List<GunRicRespAddressProtocol.AddressItem> addresses = GunRicCenterStdRecordManage.Instance.getHinstance().getFirstRecord().getAddress(gunRicCdtInterface);
        GunRicRespAddressProtocol ricRespAddressProtocol = new GunRicRespAddressProtocol();
        ricRespAddressProtocol.pushAddressList(addresses);
        return ricRespAddressProtocol;
    }
}
