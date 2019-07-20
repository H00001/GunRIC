package top.gunplan.ric.center.manage;

import top.gunplan.ric.protocol.BaseGunRicCdt;

/**
 * GunRicProviderClient
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 20:16
 */
public interface GunRicProviderClient extends GunRicClient {

    BaseGunRicCdt cdt();

    byte flag();

    boolean doCheck();




}
