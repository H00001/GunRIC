package top.gunplan.ric.center;


import top.gunplan.ric.center.common.protocol.GunRICClusterInformation;
import top.gunplan.ric.center.common.protocol.GunRICClusterSynchroizedInformation;
import top.gunplan.ric.common.F;
import top.gunplan.ric.common.GunRicBaseHandle;
import top.gunplan.ric.protocol.GunIllegalProtocolException;
import top.gunplan.ric.protocol.GunRicInputProtocol;
import top.gunplan.ric.stand.*;


/**
 * @author dosdrtt
 */
public abstract class AbstractGunRicBaseCenterHandle implements GunRicBaseHandle {
    @Override
    public GunRicInvokeResStand dealEvent(GunRicInvokeReqStand protocol) {
        F.LOG.error("error protocol is GunRicInputProtocol", getClass().getSimpleName());
        throw new GunIllegalProtocolException(GunRicInputProtocol.class, GunIllegalProtocolException.GunRicAcceptProtocolTypes.GunRicCenterAcceptProtocol);
    }

    /**
     * dealEvent
     *
     * @param protocol GunRicRegisterProtocol
     * @return AbstractGunRicProtocol to transfer
     */
    @Override
    public abstract GunRicRegisterStateStand dealEvent(GunRicRegisterStand protocol);

    /**
     * dealEvent
     *
     * @param protocol dealEvent
     * @return GunNetOutputInterface to transfer
     */
    @Override
    public abstract GunRicRetAddressStand dealEvent(GunRicGetAddressStand protocol);


    @Override
    public GunRicCenterInlineStand dealEvent(GunRicCenterInlineStand protocol) {
        if (protocol instanceof GunRICClusterInformation) {
            return dealEvent((GunRICClusterInformation) protocol);
        }
        return null;
    }


    /**
     * deal cluster information
     *
     * @param protocol information
     * @return result
     */
    public abstract GunRICClusterSynchroizedInformation dealEvent(GunRICClusterInformation protocol);


}
