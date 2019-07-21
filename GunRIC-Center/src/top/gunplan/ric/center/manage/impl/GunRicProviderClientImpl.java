package top.gunplan.ric.center.manage.impl;

import top.gunplan.ric.center.common.GunChannels;
import top.gunplan.ric.center.manage.GunRicOperator;
import top.gunplan.ric.center.manage.GunRicProviderClient;
import top.gunplan.ric.common.F;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;
import top.gunplan.ric.protocol.GunRicHelloProtocol;
import top.gunplan.ric.stand.GunRicHelloStand;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;

/**
 * GunRicProviderClientImpl
 *
 * @author frank albert
 * @version 0.0.0.3
 * @date 2019-07-19 20:02
 */
public class GunRicProviderClientImpl implements GunRicProviderClient {

    private GunRicOperator operator;
    private volatile SocketChannel channel;
    private final GunAddressItemInterface address;
    private final BaseGunRicCdt cdt;
    private int reTimes = 0;


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
            F.LOG.error(e);
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
            F.LOG.error(e);
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
        if (!GunChannels.channelAvailable(channel)) {
            init();
            if (!GunChannels.channelAvailable(channel)) {
                update();
                return false;
            }
        }
        try {
            GunRicHelloStand hello = new GunRicHelloProtocol(true);
            short number = (short) hello.serialNumber();
            GunChannels.channelWrite(channel, hello.serialize());
            byte[] b = GunChannels.channelRead(channel, 8);
            if (b != null) {
                hello.unSerialize(b);
                if (number + 1 == hello.serialNumber()) {
                    F.LOG.info("connect is normal now");
                } else {
                    F.LOG.info("connect is not now " + number + " " + hello.serialNumber());
                }
            } else {
                channel = null;
            }

        } catch (IOException e) {
            F.LOG.error(e);
        }
        return true;
    }

    @Override
    public int reConnectionTimes() {
        return reTimes;
    }

    @Override
    public void clean() {
        reTimes = 0;
    }

    @Override
    public void update() {
        reTimes++;
    }
}
