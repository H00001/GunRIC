package top.gunplan.ric.stand;

import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Set;

/**
 * GunRicRetAddressStand
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-07-19 00:30
 */
public interface GunRicRetAddressStand extends GunRicBaseStand {

    Set<GunAddressItemInterface> addressItems();


    void pushAddress(GunAddressItemInterface ad);


}
