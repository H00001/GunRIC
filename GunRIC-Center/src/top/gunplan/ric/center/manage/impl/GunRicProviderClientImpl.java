package top.gunplan.ric.center.manage.impl;

import top.gunplan.ric.center.common.GunChannels;
import top.gunplan.ric.center.manage.GunRicProviderClient;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;
import top.gunplan.ric.protocol.GunRicHelloProtocol;
import top.gunplan.ric.stand.GunRicHelloStand;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

/**
 * GunRicProviderClientImpl
 *
 * @author frank albert
 * @version 0.0.0.3
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
        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(address.getInet());
        } catch (IOException e) {
            AbstractGunBaseLogUtil.error(e);
            return -2;
        }
        this.channel = socketChannel;
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
        if (channel == null) {
            init();
        }
        try {
            GunChannels.channelWrite(channel, new GunRicHelloProtocol(true).serialize());
            GunRicHelloStand hello = new GunRicHelloProtocol();
            System.out.println("blockinv");
            byte[] b = GunChannels.channelRead(channel, 8);
            if (b != null) {
                hello.unSerialize(b);
            }
            System.out.println("blockinv1");
        } catch (IOException e) {
            AbstractGunBaseLogUtil.error(e);
        }
        return true;
    }
}
