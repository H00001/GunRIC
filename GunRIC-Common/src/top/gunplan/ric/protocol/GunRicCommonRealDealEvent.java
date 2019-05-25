package top.gunplan.ric.protocol;

/**
 * @param <F> GunRicInputProtocol
 * @param <T> GunRicOutputProtocol
 */
@FunctionalInterface
public interface GunRicCommonRealDealEvent<F extends AbstractGunRicProtocol, T extends AbstractGunRicProtocol> {
    /**
     * @param from
     * @return
     */
    T dealEvent(F from);
}
