package top.gunplan.ric.protocol;

import java.net.InetSocketAddress;

/**
 * GunAddressItemInterface
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-12 21:44
 */

public interface GunAddressItemInterface {
    /**
     * getInet
     *
     * @return InetSocketAddress
     */
    InetSocketAddress getInet();

    /**
     * getPort
     *
     * @return int
     */
    int getPort();

    /**
     * getAddress
     *
     * @return String
     */
    String getAddress();

    /**
     * setAddress
     *
     * @param address InetSocketAddress
     */
    void setAddress(InetSocketAddress address);
}
