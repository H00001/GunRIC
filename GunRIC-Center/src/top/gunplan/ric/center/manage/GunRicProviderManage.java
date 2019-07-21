package top.gunplan.ric.center.manage;

import java.util.Set;

/**
 * GunRicProviderManage
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 19:57
 */

public interface GunRicProviderManage extends GunRicClientManager<GunRicProviderClient> {

    Set<GunRicProviderClient> removeUnuseProvider();
}
