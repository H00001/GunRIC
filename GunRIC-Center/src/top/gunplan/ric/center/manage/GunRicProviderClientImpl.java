package top.gunplan.ric.center.manage;

import top.gunplan.ric.center.common.GunChannels;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;
import top.gunplan.ric.protocol.GunRicHelloProtocol;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;

/**
 * GunRicProviderClientImpl
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 20:02
 */
public class GunRicProviderClientImpl implements GunRicProviderClient {

    private volatile SocketChannel channel;
    private final GunAddressItemInterface address;
    private final BaseGunRicCdt cdt;


    public GunRicProviderClientImpl(GunAddressItemInterface address, BaseGunRicCdt cdt) {
        this.address = address;
        this.cdt = cdt;
    }

    @Override
    public int init() {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            SocketAddress socketAddress = address.getInet();
            try {
                socketChannel.connect(socketAddress);
            } catch (IOException e) {
                AbstractGunBaseLogUtil.error(e);
                return -2;
            }
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            return -1;
        }
        return 0;

    }


    @Override
    public int destory() {
        try {
            channel.close();
        } catch (IOException e) {
            AbstractGunBaseLogUtil.error(e);
            return -1;
        }
        return 0;
    }

    @Override
    public Channel channel() {
        return channel;
    }

    @Override
    public BaseGunRicCdt cdt() {
        return cdt;
    }

    @Override
    public GunAddressItemInterface addressInformation() {
        return address;
    }

    @Override
    public byte flag() {
        //not use
        return 0;
    }

    @Override
    public boolean doCheck() {
        try {
            if (GunChannels.ClannelAvaliable(channel)) {
                channel.write(ByteBuffer.wrap(new GunRicHelloProtocol(true).serialize()));
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                channel.read(buffer);
            }
        } catch (IOException e) {
            AbstractGunBaseLogUtil.error(e);
        }
        return true;
    }
}
