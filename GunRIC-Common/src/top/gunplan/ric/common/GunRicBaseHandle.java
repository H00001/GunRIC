package top.gunplan.ric.common;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.netty.protocol.GunNetInbound;
import top.gunplan.netty.protocol.GunNetOutBound;
import top.gunplan.ric.protocol.GunIllegalProtocolException;
import top.gunplan.ric.protocol.GunRicCommonRealDealEvent;
import top.gunplan.ric.protocol.RicProtocolCode;
import top.gunplan.ric.stand.*;
import top.gunplan.utils.GunLogger;

import java.net.SocketAddress;

/**
 * @author dosdrtt
 */
public interface GunRicBaseHandle extends GunNettyHandle {
    GunLogger LOG = GunNettyContext.logger.setTAG(GunRicBaseHandle.class);

    /**
     * dealEvent
     * hello message send auto
     *
     * @param protocol GunRicHelloStand
     * @return AbstractGunRicProtocol
     */
    default GunRicHelloStand dealEvent(GunRicHelloStand protocol) {
        if (protocol.code() == RicProtocolCode.HELLO_REQ) {
            protocol.setCode(RicProtocolCode.HELLO_RES);
            protocol.incrSeq();
            final GunRicBaseStand next = protocol.next();
            protocol.next(next == null ? null : dealDataEvent(next));
            return protocol;
        } else {
            return null;
        }
    }


    /**
     * dealMuchEvent
     *
     * @param dealHandle handle to real deal event
     * @param protocol   protocol
     * @return GunNetOutputInterface
     */
    default <I extends GunRicBaseStand, O extends GunRicBaseStand> O dealMuchEvent(GunRicCommonRealDealEvent<I, O> dealHandle, I protocol) {
        final O baseProtocol = dealHandle.dealEvent(protocol);
        final GunRicBaseStand next = protocol.next();
        baseProtocol.next(next == null ? null : dealDataEvent(next));
        return baseProtocol;
    }

    /**
     * dealEvent provider used
     *
     * @param protocol GunRicInputProtocol
     * @return AbstractGunRicProtocol
     */
    GunRicInvokeResStand dealEvent(GunRicInvokeReqStand protocol);

    /**
     * dealEvent center used
     *
     * @param protocol GunRicRegisterStand
     * @return AbstractGunRicProtocol
     */
    GunRicRegisterStateStand dealEvent(GunRicRegisterStand protocol);

    /**
     * GunRicGetAddressProtocol center used
     *
     * @param protocol dealEvent
     * @return AbstractGunRicProtocol
     */
    GunRicRetAddressStand dealEvent(GunRicGetAddressStand protocol);


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
    default void dealExceptionEvent(GunChannelException e) {
        LOG.error(e);
    }

    /**
     * connection happened
     *
     * @param socketAddress SocketAddress
     * @return output
     * @throws GunException kinds of exception
     */
    @Override
    GunNetOutBound dealConnEvent(SocketAddress socketAddress) throws GunException;

    /**
     * dealDataEvent
     * <p>
     * bus of input
     *
     * @param var1 GunNetInbound
     * @return GunRicBaseStand
     * @throws GunException kinds of exceptions
     */
    @Override
    default GunRicBaseStand dealDataEvent(GunNetInbound var1) throws GunException {
        try {

            if (var1 instanceof GunRicHelloStand) {
                return dealEvent((GunRicHelloStand) (var1));
            } else if (var1 instanceof GunRicInvokeReqStand) {
                return dealEvent((GunRicInvokeReqStand) (var1));
            } else if (var1 instanceof GunRicRegisterStand) {
                return dealEvent((GunRicRegisterStand) var1);
            } else if (var1 instanceof GunRicGetAddressStand) {
                return dealEvent((GunRicGetAddressStand) var1);
            } else {
                LOG.error("not known packet" + var1.getClass().getName(), getClass().getSimpleName());
                return null;
            }
        } catch (GunIllegalProtocolException exp) {
            LOG.error(exp);
            return null;
        }
    }

}
