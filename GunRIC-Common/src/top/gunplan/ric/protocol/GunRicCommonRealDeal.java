package top.gunplan.ric.protocol;


/**
 * real deal handle
 * GunRicCommonRealDeal
 *
 * @author dosdrtt
 */
public interface GunRicCommonRealDeal<I extends AbstractGunRicProtocol, O extends AbstractGunRicProtocol> {
    /**
     * dealDataEvent
     *
     * @param protocol AbstractGunRicProtocol
     * @return AbstractGunRicProtocol to transfer to client
     */
    O dealDataEvent(I protocol);
}