package top.gunplan.RIC.center;

import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.*;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * GunRicCenterHandle
 *
 * @author dosdrtt
 */
public class GunRicCenterHandle extends AbstractGunRicBaseCenterHandle {
    @Override
    public AbstractGunRicProtocol dealEvent(GunRicRegisterProtocol protocol) {
        return null;
    }

    private AbstractGunRicProtocol realDealEvent(AbstractGunRicProtocol protocol) {
        List<InetSocketAddress> addresses = GunRicInterfaceBuffer.intermapping.get(new GunRicInterfaceBuffer.GunRicCdtInterface((AbstractCenterHelperProtocol) protocol));
        GunRicRespAddressProtocol respprotocol = new GunRicRespAddressProtocol();
        respprotocol.pushAddressList(addresses);
        return respprotocol;
    }

    @Override
    public GunNetOutputInterface dealEvent(GunRicGetAddressProcotol protocol) {
        if (protocol.getNext() == null) {
            return realDealEvent(protocol);
        } else {
            return dealMuchEvent(this::realDealEvent, protocol);
        }
    }


}