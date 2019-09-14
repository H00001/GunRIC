package top.gunplan.ric.provider;

import top.gunplan.ric.common.GunRicBaseHandle;
import top.gunplan.ric.protocol.GunIllegalProtocolException;
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
        threwError(protocol);
        return null;
    }

    private void threwError(GunRicBaseStand protocol) {
        throw new GunIllegalProtocolException(protocol.getClass(), GunIllegalProtocolException.GunRicAcceptProtocolTypes.GunRicProviderAcceptProtocol);
    }

    @Override
    public GunRicRetAddressStand dealEvent(GunRicGetAddressStand protocol) {
        threwError(protocol);
        return null;
    }


    @Override
    public GunRICCenterInlineStand dealEvent(GunRICCenterInlineStand protocol) {
        threwError(protocol);
        return null;
    }

    @Override
    public void dealCloseEvent(SocketAddress socketAddress) {

    }
}
