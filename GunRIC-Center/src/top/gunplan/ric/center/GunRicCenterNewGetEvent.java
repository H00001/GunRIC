package top.gunplan.ric.center;

import top.gunplan.ric.protocol.AbstractCenterHelperProtocol;
import top.gunplan.ric.protocol.AbstractGunRicProtocol;
import top.gunplan.ric.protocol.GunRicCommonRealDeal;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author dosdrtt
 * @date 2019/05/23
 */
public class GunRicCenterNewGetEvent implements GunRicCommonRealDeal {
    @Override
    public AbstractGunRicProtocol dealDataEvent(AbstractGunRicProtocol protocol) {
        List<InetSocketAddress> addresses = GunRicInterfaceBuffer.intermapping.get(new GunRicInterfaceBuffer.GunRicCdtInterface((AbstractCenterHelperProtocol) protocol));
        GunRicRespAddressProtocol ricRespAddressProtocol = new GunRicRespAddressProtocol();
        ricRespAddressProtocol.pushAddressList(addresses);
        return ricRespAddressProtocol;
    }
}
