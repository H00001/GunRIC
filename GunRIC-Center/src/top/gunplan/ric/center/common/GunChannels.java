package top.gunplan.ric.center.common;

import java.nio.channels.Channel;

/**
 * GunChannels
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 21:18
 */
public final class GunChannels {
    public static boolean ClannelAvaliable(Channel channel) {
        return channel != null && (channel.isOpen());
    }
}
