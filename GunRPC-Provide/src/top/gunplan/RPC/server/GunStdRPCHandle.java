package top.gunplan.RPC.server;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import protocol.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.ServiceLoader;

public class GunStdRPCHandle implements GunNettyHandle {
    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface gunNetInputInterface) {
        GunRPCOutputProtocl protocl = new GunRPCOutputProtocl();
        GunRPCInputProtocl protocl1 = ((GunRPCInputProtocl) gunNetInputInterface);
        protocl.setType(RPCProtoclType.RESPONSE);
        protocl.setCode(RPCProtoclCode.SUCCEED);
        Object oc = null;
        try {

            Class<?> inst = Class.forName(protocl1.getInterfaceName());
            ServiceLoader<?> spiLoader = ServiceLoader.load(inst);
            Iterator<?> iteratorSpi = spiLoader.iterator();
            Object dubboService = iteratorSpi.next();
            Method realmd = null;
            Method[] md = dubboService.getClass().getMethods();
            for (Method mdds : md) {
                if (mdds.getName().equals(protocl1.getMethodName())) {
                    realmd = mdds;
                    break;
                }
            }
            assert realmd != null;
            if (protocl1.getParamleng() != 0) {
                oc = realmd.invoke(dubboService, protocl1.getParameters()[0]);
            } else {
                oc = realmd.invoke(dubboService);
            }

        } catch (ClassNotFoundException | IllegalAccessException  | InvocationTargetException e) {
            e.printStackTrace();
        }
        protocl.setReturnValue(oc);
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
