package top.gunplan.ric.center.manage;

import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Set;
import java.util.stream.Stream;

/**
 * GunRicClientManager
 *
 * @author frank albert
 * @version 0.0.0.2
 * #date 2019-07-19 20:13
 */

public interface GunRicClientManager<U extends GunRicClient> {

    /**
     * get client list
     *
     * @return list;client list
     */
    Set<U> clientList();

    /**
     * normal size
     * @return size
     */
    int normalSize();

    GunProviderAliveCheckResult aliveCheck();

    void register(GunAddressItemInterface user, BaseGunRicServerInformation cdt);

    U removeById(long id);

    void remove(U u);


    void informToRecorder(Stream<U> stream);
}
