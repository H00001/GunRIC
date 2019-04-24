package top.gunplan.RPC.server;

import top.gunplan.RPC.APIS.test.anno.GunUseImpl;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import protocol.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.SocketChannel;

/**
 * @author dosdrtt
 */
public class GunStdRPCHandle implements GunNettyHandle {

    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface gunNetInputInterface) {
        final GunRPCOutputProtocl outputprotocl = new GunRPCOutputProtocl();
        final GunRPCInputProtocl inoutprotocl = ((GunRPCInputProtocl) gunNetInputInterface);
        outputprotocl.setType(RPCProtoclType.RESPONSE);
        try {
            Class<?> inst = Class.forName(inoutprotocl.getInterfaceName());
            GunUseImpl anno = inst.getAnnotation(GunUseImpl.class);
            Object rpcService = Class.forName(anno.impl()).newInstance();
            Method realmd = null;
            for (Method md : inst.getMethods()) {
                if (md.getName().equals(inoutprotocl.getMethodName())) {
                    realmd = md;
                    break;
                }
            }
            assert realmd != null;
            Object oc = inoutprotocl.getParamleng() == 0 ? realmd.invoke(rpcService) : realmd.invoke(rpcService, inoutprotocl.getParameters());
            outputprotocl.setCode(RPCProtoclCode.SUCCEED);
            outputprotocl.setReturnValue(oc);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            outputprotocl.setCode(RPCProtoclCode.FAIL);
        } catch (InstantiationException e) {
            outputprotocl.setCode(RPCProtoclCode.FAIL);
            e.printStackTrace();
        }

        return outputprotocl;
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
