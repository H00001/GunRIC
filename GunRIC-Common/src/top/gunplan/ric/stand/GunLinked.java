package top.gunplan.ric.stand;

/**
 * GunLinked
 * <p>
 * some one is can linked
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-07-20 08:55
 */
public interface GunLinked<N> {
    /**
     * get next element
     *
     * @return next element  if it is null that it is null
     */
    N next();


    /**
     * set next
     *
     * @param n next element
     */

    void next(N n);
}
