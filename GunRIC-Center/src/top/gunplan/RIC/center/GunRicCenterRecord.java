package top.gunplan.RIC.center;

import top.gunplan.netty.GunHandle;

import java.net.InetSocketAddress;

/**
 * GunRicCenterRecord
 *
 * @author dosdrtt
 */
public interface GunRicCenterRecord extends GunHandle {
    /**
     * when record first request
     *
     * @param g       ric record from information
     * @param address address info
     */

    void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address);

    /**
     * when record next request
     *
     * @param g       ric record from information
     * @param address address info
     */
    void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address);
}
