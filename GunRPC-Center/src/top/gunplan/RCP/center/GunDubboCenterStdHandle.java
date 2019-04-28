package top.gunplan.RCP.center;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.protocol.GunNetInputInterface;
import top.gunplan.protocol.GunNetOutputInterface;

import java.nio.channels.SocketChannel;

public class GunDubboCenterStdHandle implements GunNettyHandle {
    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface request) throws GunException, ClassNotFoundException {
        return null;
    }

    @Override
    public GunNetOutputInterface dealConnEvent(SocketChannel request) throws GunException {
        return null;
    }

    @Override
    public void dealCloseEvent() {

    }

    @Override
    public void dealExceptionEvent(Exception exp) {

    }
}
