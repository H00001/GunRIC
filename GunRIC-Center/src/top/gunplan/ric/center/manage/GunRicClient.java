package top.gunplan.ric.center.manage;

import top.gunplan.netty.GunHandle;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.nio.channels.Channel;

/**
 * GunRicClient
 * <p>
 * client include provider and consumer and so on
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 20:14
 */
public interface GunRicClient extends GunHandle, GunRICStateRecorder, Comparable<GunRicClient> {
    /**
     * get channel
     *
     * @return channel
     */
    Channel channel();

    @Override
    default int compareTo(GunRicClient o) {
        return Integer.compare(hashCode(), o.hashCode());
    }


    /**
     * address master
     *
     * @return master
     */
    GunAddressItemInterface addressInformation();
}
