package top.gunplan.ric.provider;

import top.gunplan.netty.GunException;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.common.GunRicBaseHandle;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.stand.*;

import java.net.SocketAddress;

/**
 * @author dosdrtt
 * @concurrent AbstractGunRicBaseProviderHandle
 */
public abstract class AbstractGunRicBaseProviderHandle implements GunRicBaseHandle {
    /**
     * dealEvent
     * <p>
     * deal GunRicInputProtocol request event
     *
     * @param protocol GunRicInputProtocol
     * @return AbstractGunRicProtocol
     */
    @Override
    public abstract GunRicInvokeResStand dealEvent(GunRicInvokeReqStand protocol);

    @Override
    public GunRicRegisterStateStand dealEvent(GunRicRegisterStand protocol) {
        throw new GunIllegalProtocolException(protocol.getClass(), GunIllegalProtocolException.GunRicAcceptProtocolTypes.GunRicProviderAcceptProtocol);
    }

    @Override
    public GunRicRetAddressStand dealEvent(GunRicGetAddressStand protocol) {
        throw new GunIllegalProtocolException(protocol.getClass(), GunIllegalProtocolException.GunRicAcceptProtocolTypes.GunRicProviderAcceptProtocol);
    }


    @Override
    public GunNetOutputInterface dealConnEvent(SocketAddress socketAddress) throws GunException {
        return null;
    }
}
