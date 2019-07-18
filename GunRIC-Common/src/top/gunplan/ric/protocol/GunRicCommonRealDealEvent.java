package top.gunplan.ric.protocol;

import top.gunplan.ric.stand.GunRicBaseStand;

/**
 * @param <F> GunRicInputProtocol
 * @param <T> GunRicOutputProtocol
 * @author dosdrtt
 */
@FunctionalInterface
public interface GunRicCommonRealDealEvent<F extends GunRicBaseStand, T extends GunRicBaseStand> {
    /**
     * dealEvent
     *
     * @param source source protocol
     * @return transfer protocol
     */
    T dealEvent(F source);
}
