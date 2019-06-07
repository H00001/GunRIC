package top.gunplan.ric.common;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author dosdrtt
 */
public interface GunRicBaseHandle extends GunNettyHandle {
    /**
     * dealEvent
     * hello message send auto
     *
     * @param protocol GunRicHelloProtocol
     * @return AbstractGunRicProtocol
     */
    default AbstractGunRicProtocol dealEvent(GunRicHelloProtocol protocol) {

        GunRicHelloProtocol protocol1 = new GunRicHelloProtocol(false);
        if (protocol.getCode() == RicProtocolCode.HELLO_REQ) {
            protocol1.setSerialnumber(protocol.getSerialnumber());
            return protocol1;
        } else {
            AbstractGunBaseLogUtil.debug("get hello protocol");
            return null;
        }
    }

    /**
     * dealMuchEvent
     *
     * @param dealhandle hanle to real deal event
     * @param protocol   prorocol
     * @return GunNetOutputInterface
     */
    default GunNetOutputInterface dealMuchEvent(GunRicCommonRealDealEvent<AbstractGunRicProtocol, AbstractGunRicProtocol> dealhandle, AbstractGunRicProtocol protocol) {
        if (protocol.getNext() == null) {
            return dealhandle.dealEvent(protocol);
        } else {
            GunCombineOutput capt = new GunCombineOutput();
            for (; protocol != null; ) {
                capt.push(dealhandle.dealEvent(protocol));
                protocol = protocol.getNext();
            }
            return capt;
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
    GunNetOutputInterface dealEvent(GunRicRegisterProtocol protocol);

    /**
     * GunRicGetAddressProtocol center used
     *
     * @param protocol dealEvent
     * @return AbstractGunRicProtocol
     */
    GunNetOutputInterface dealEvent(GunRicGetAddressProtocol protocol);

    /**
     * @param var1 GunNetInputInterface
     * @return GunNetOutputInterface
     * @throws GunException kinds of exceptions
     */
    @Override
    GunNetOutputInterface dealDataEvent(GunNetInputInterface var1);

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
    GunNetOutputInterface dealConnEvent(SocketAddress socketAddress) throws GunException;
}
