package top.gunplan.ric.center;


import top.gunplan.netty.GunException;
import top.gunplan.netty.protocol.GunNetOutputInterface;

import top.gunplan.ric.common.GunRicBaseHandle;
import top.gunplan.ric.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.net.SocketAddress;


/**
 * @author dosdrtt
 */
public abstract class AbstractGunRicBaseCenterHandle implements GunRicBaseHandle {
    @Override
    public AbstractGunRicProtocol dealEvent(GunRicInputProtocol protocol) {
        AbstractGunBaseLogUtil.error("error protocol is GunRicInputProtocol", getClass().getSimpleName());
        throw new GunIllegalProtocolException(GunRicInputProtocol.class, GunIllegalProtocolException.GunRicAcceptProtocolTypes.GunRicCenterAcceptProtocol);
    }

    /**
     * dealEvent
     *
     * @param protocol GunRicRegisterProtocol
     * @return AbstractGunRicProtocol to transfer
     */
    @Override
    public abstract GunNetOutputInterface dealEvent(GunRicRegisterProtocol protocol);

    /**
     * dealEvent
     *
     * @param protocol dealEvent
     * @return GunNetOutputInterface to transfer
     */
    @Override
    public abstract GunNetOutputInterface dealEvent(GunRicGetAddressProtocol protocol);


    @Override
    public GunNetOutputInterface dealConnEvent(SocketAddress socketAddress) throws GunException {
        return null;
    }
}
