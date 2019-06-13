package top.gunplan.ric.provider;

import top.gunplan.netty.GunException;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.common.GunRicBaseHandle;
import top.gunplan.ric.protocol.*;

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
    public abstract GunNetOutputInterface dealEvent(GunRicInputProtocol protocol);

    @Override
    public AbstractGunRicProtocol dealEvent(GunRicRegisterProtocol protocol) {
        throw new GunIllegalProtocolException(GunRicRegisterProtocol.class, GunIllegalProtocolException.GunRicAcceptProtocolTypes.GunRicProviderAcceptProtocol);
    }

    @Override
    public AbstractGunRicProtocol dealEvent(GunRicGetAddressProtocol protocol) {
        throw new GunIllegalProtocolException(GunRicRegisterProtocol.class, GunIllegalProtocolException.GunRicAcceptProtocolTypes.GunRicProviderAcceptProtocol);
    }


    @Override
    public GunNetOutputInterface dealConnEvent(SocketAddress socketAddress) throws GunException {
        return null;
    }
}
