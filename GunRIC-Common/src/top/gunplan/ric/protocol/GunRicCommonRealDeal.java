package top.gunplan.ric.protocol;

/**
 * real deal handle
 * GunRicCommonRealDeal
 * @author dosdrtt
 */
public interface GunRicCommonRealDeal {
    /**
     * dealDataEvent
     * @param protocol AbstractGunRicProtocol
     * @return AbstractGunRicProtocol to transfer to client
     */
    AbstractGunRicProtocol dealDataEvent(AbstractGunRicProtocol protocol);
}
