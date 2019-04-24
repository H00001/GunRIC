package top.gunplan.RPC.server;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.*;

import java.nio.channels.SocketChannel;

public class GunStdRPCHandle implements GunNettyHandle {
    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface gunNetInputInterface) throws GunException {
        GunRPCOutputProtocl protocl = new GunRPCOutputProtocl();
        protocl.setType(RPCProtoclType.RESPONSE);
        protocl.setCode(RPCProtoclCode.SUCCEED);
        protocl.setReturnValue("htllo");
        return protocl;
    }

    @Override
    public GunNetOutputInterface dealConnEvent(SocketChannel socketChannel) throws GunException {
        return null;
    }

    @Override
    public void dealCloseEvent() {

    }

    @Override
    public void dealExceptionEvent(Exception e) {

    }
}
