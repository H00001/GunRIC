package top.gunplan.ric.center;

import top.gunplan.netty.GunHandle;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

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

    void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunRicRespAddressProtocol.AddressItem address);

    /**
     * when record next request
     *
     * @param g       ric record from information
     * @param address address info
     */
    void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunRicRespAddressProtocol.AddressItem address);


}
