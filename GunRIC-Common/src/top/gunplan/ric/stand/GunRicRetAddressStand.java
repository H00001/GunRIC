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

    /**
     * get available address items
     *
     * @return address
     */
    Set<GunAddressItemInterface> addressItems();


    /**
     * push address into stand
     * @param ad address
     */
    void pushAddress(GunAddressItemInterface ad);


}
