package top.gunplan.ric.center;

import top.gunplan.netty.GunHandle;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;

/**
 * GunRicCenterRecord
 *
 * @author dosdrtt
 * @date 2019/05/28
 */
public interface GunRicCenterRecord extends GunHandle {
    /**
     * when record first request
     *
     * @param g       ric record from information
     * @param address address info
     */

    void firstAdd(BaseGunRicCdt g, GunAddressItemInterface address);

    /**
     * when record next request
     *
     * @param g       ric record from information
     * @param address address info
     */
    void nextAdd(BaseGunRicCdt g, GunAddressItemInterface address);


}
