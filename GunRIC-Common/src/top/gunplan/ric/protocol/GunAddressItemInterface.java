package top.gunplan.ric.protocol;

import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.GunBytesUtil;

import java.net.InetSocketAddress;

/**
 * GunAddressItemInterface
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-12 21:44
 */

public interface GunAddressItemInterface extends GunNetOutputInterface, GunNetInputInterface, GunRicOutputHelper,
        GunRicNxInput {

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


    int FLAG_LEN = 8;
    int ADD_LEN = 8;
    int PORT_LEN = 2;
    int NEED_SPACE = FLAG_LEN + ADD_LEN + PORT_LEN;

    /**
     * unSerialize new way
     *
     * @param stream read stream
     * @return boolean
     */
    @Override
    boolean unSerialize(GunBytesUtil.GunReadByteStream stream);




}
