package top.gunplan.ric.center.common;


import top.gunplan.ric.center.context.F;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;

/**
 * ChannelUtil
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 21:18
 */
public final class ChannelUtil {

    public static SocketChannel connect(final InetSocketAddress address) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(address);
        } catch (IOException e) {
            F.LOG.error(String.format("connection %s:%d fail", address.getHostString(), address.getPort()));
        }
        return socketChannel;
    }

    public static boolean channelAvailable(Channel channel) {
        return channel != null && (channel.isOpen());
    }

    public static boolean channelWrite(SocketChannel channel, byte[] data) throws SocketException {
        if (channelAvailable(channel)) {
            try {
                channel.write(ByteBuffer.wrap(data));
                return true;
            } catch (IOException e) {
                F.LOG.error(e);

                return false;
            }
        } else {
            throw new SocketException("Channel can not use");
        }
    }

    public static byte[] channelRead(SocketChannel channel, int size) throws IOException {
        byte[] realSave = null;
        if (channelAvailable(channel)) {
            ByteBuffer buffer = ByteBuffer.allocate(size);
            int len = channel.read(buffer);
            if (len == -1) {
                F.LOG.debug("node " + channel.getRemoteAddress() + " connected error");
                return null;
            }
            realSave = new byte[len];
            System.arraycopy(buffer.array(), 0, realSave, 0, len);
        }
        return realSave;
    }
}
