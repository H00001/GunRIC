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
public interface GunRicClient extends GunHandle {
    Channel channel();

    GunAddressItemInterface addressInformation();
}
