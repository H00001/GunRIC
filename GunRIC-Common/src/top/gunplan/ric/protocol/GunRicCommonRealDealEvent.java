package top.gunplan.ric.protocol;

/**
 * @param <F> GunRicInputProtocol
 *
 * @author dosdrtt
 * @param <T> GunRicOutputProtocol
 */
@FunctionalInterface
public interface GunRicCommonRealDealEvent<F extends AbstractGunRicProtocol, T extends AbstractGunRicProtocol> {
    /**
     * dealEvent
     * @param source source protocol
     * @return transfer protocol
     */
    T dealEvent(F source);
}
