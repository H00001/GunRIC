package top.gunplan.ric.protocol;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.nio.channels.SocketChannel;

/**
 * @author dosdrtt
 */
public interface GunDubboBaseHandle extends GunNettyHandle {
    /**
     * @param protocol GunRicHelloProtocol
     * @return AbstractGunRicProtocol
     */
    default AbstractGunRicProtocol dealEvent(GunRicHelloProtocol protocol) {
        GunRicHelloProtocol protocol1 = new GunRicHelloProtocol(false);
        if (protocol.getCode() == RicProtocolCode.HELLO_REQ) {
            protocol1.setSerialnumber(protocol.getSerialnumber());
            return protocol1;
        } else {
            return null;
        }
    }

    /**
     * dealEvent provider used
     *
     * @param protocol GunRicInputProtocol
     * @return AbstractGunRicProtocol
     */
    GunNetOutputInterface dealEvent(GunRicInputProtocol protocol);

    /**
     * dealEvent center used
     *
     * @param protocol GunRicRegisterProtocol
     * @return AbstractGunRicProtocol
     */
    AbstractGunRicProtocol dealEvent(GunRicRegisterProtocol protocol);

    /**
     * GunRicGetAddressProcotol center used
     *
     * @param protocol dealEvent
     * @return AbstractGunRicProtocol
     */
    AbstractGunRicProtocol dealEvent(GunRicGetAddressProcotol protocol);

    /**
     * @param var1 GunNetInputInterface
     * @return GunNetOutputInterface
     * @throws GunException kinds of exceptions
     */
    @Override
    default GunNetOutputInterface dealDataEvent(GunNetInputInterface var1) throws GunException {
        return null;
    }

    /**
     * deal colse event
     */
    @Override
    default void dealCloseEvent() {
        //do nothing
    }

    /**
     * dealExceptionEvent
     *
     * @param e Exception
     */
    @Override
    default void dealExceptionEvent(Exception e) {
        AbstractGunBaseLogUtil.error(e);
    }


    @Override
    default GunNetOutputInterface dealConnEvent(SocketChannel socketChannel) throws GunException {
        return null;
    }
}
