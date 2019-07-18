package top.gunplan.ric.protocol;


import top.gunplan.ric.stand.GunRicBaseStand;

/**
 * real deal handle
 * GunRicCommonRealDeal
 *
 * @author dosdrtt
 */
public interface GunRicCommonRealDeal<I extends GunRicBaseStand, O extends GunRicBaseStand> {
    /**
     * dealDataEvent
     *
     * @param protocol AbstractGunRicProtocol
     * @return AbstractGunRicProtocol to transfer to client
     */
    O dealDataEvent(I protocol);
}