package top.gunplan.RPC.server;

import top.gunplan.RPC.APIS.test.anno.GunUseImpl;
import netty.GunException;
import netty.GunNettyHandle;
import protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

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
            Object rpcService = Class.forName(inst.getAnnotation(GunUseImpl.class).impl()).newInstance();
            Method realmd = null;
            for (Method md : inst.getMethods()) {
                if (md.getName().equals(inoutprotocl.getMethodName())) {
                    realmd = md;
                    break;
                }
            }
            if (realmd == null) {
                outputprotocl.setCode(RPCProtoclCode.FAIL);
                outputprotocl.setReturnValue("method not found ");
                AbstractGunBaseLogUtil.error(inoutprotocl.getMethodName(), "method not found", "[PROVIDE]");
                return outputprotocl;
            }
            Object oc = inoutprotocl.getParamleng() == 0 ? realmd.invoke(rpcService) : realmd.invoke(rpcService, inoutprotocl.getParameters());
            outputprotocl.setCode(RPCProtoclCode.SUCCEED);
            outputprotocl.setReturnValue(oc);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            outputprotocl.setCode(RPCProtoclCode.FAIL);
            this.dealExceptionEvent(e);
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
        e.printStackTrace();
    }
}
