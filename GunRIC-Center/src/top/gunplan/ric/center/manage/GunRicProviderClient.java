package top.gunplan.ric.center.manage;

import top.gunplan.ric.protocol.BaseGunRicCdt;

import java.util.Set;

/**
 * GunRicProviderClient
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 20:16
 */
public interface GunRicProviderClient extends GunRicClient {

    Set<BaseGunRicCdt> cdt();

    void addCdt(BaseGunRicCdt c);

    byte flag();

    boolean doCheck();


}
