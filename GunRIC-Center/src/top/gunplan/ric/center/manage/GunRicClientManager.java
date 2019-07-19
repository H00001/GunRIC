package top.gunplan.ric.center.manage;

import java.util.List;

/**
 * GunRicClientManager
 *
 * @author frank albert
 * @version 0.0.0.2
 * @date 2019-07-19 20:13
 */

public interface GunRicClientManager<U extends GunRicClient> {

    List<U> clientList();

    GunProviderAliveCheckResult aliveCheck();

    void register(U user);

    U removeById(long id);

    void remove(U u);
}
