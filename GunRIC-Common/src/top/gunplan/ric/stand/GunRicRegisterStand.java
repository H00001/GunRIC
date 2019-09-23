package top.gunplan.ric.stand;

import top.gunplan.netty.impl.GunNetInBoundOutBound;

/**
 * GunRicRegisterStand
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-07-19 00:03
 */
public interface GunRicRegisterStand extends GunRicParamBaseStand, GunNetInBoundOutBound {

    /**
     * ip address
     *
     * @return address like 127.0.0.1,0::1
     */
    String ipAddress();

    /**
     * port number
     * @return port like 80
     */
    int portNumber();

}
