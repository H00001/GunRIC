package top.gunplan.ric.center.manage;

/**
 * GunRicOperator
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-07-20 21:57
 */

@FunctionalInterface
public interface GunRicOperator {
    void operator(GunRicClient client);
}
