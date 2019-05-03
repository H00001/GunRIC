package top.gunplan.RPC.server;

import top.gunplan.RPC.APIS.test.anno.GunUseImpl;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.protocol.*;
import top.gunplan.netty.protocol.GunNetOutputInterface;
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
        AbstractGunRPCExecuteProtocol.ParamHelper help = new AbstractGunRPCExecuteProtocol.ParamHelper();
        final GunRPCOutputProtocl outputprotocl = new GunRPCOutputProtocl();
        final GunRPCInputProtocl inputprotocl = ((GunRPCInputProtocl) gunNetInputInterface);
        outputprotocl.setReturnValue(help);
        outputprotocl.setType(RPCProtoclType.RESPONSE);
        try {
            if (invokeMethod(help, outputprotocl, inputprotocl)) return outputprotocl;
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            help.setObj(e.getMessage());
            this.dealExceptionEvent(e);
        } catch (Exception exp) {
            this.dealExceptionEvent(exp);
        }
        return outputprotocl;
    }

    private boolean invokeMethod(AbstractGunRPCExecuteProtocol.ParamHelper help, GunRPCOutputProtocl outputprotocl, GunRPCInputProtocl inoutprotocl) throws Exception {
        Class<?> inst = Class.forName(inoutprotocl.getInterfaceName());
        Object rpcService = Class.forName(inst.getAnnotation(GunUseImpl.class).impl()).newInstance();
        Method realmd = inst.getMethod(inoutprotocl.getMethodName(), inoutprotocl.getParamTypeList());
        if (realmd == null) {
            outputprotocl.setCode(RPCProtoclCode.FAIL);
            help.setObj("method not found ");
            AbstractGunBaseLogUtil.error(inoutprotocl.getMethodName(), "method not found", "[PROVIDE]");
            return false;
        }
        help.setObj(inoutprotocl.getParamleng() == 0 ? realmd.invoke(rpcService) : realmd.invoke(rpcService, inoutprotocl.getParameters()));
        outputprotocl.setCode(RPCProtoclCode.SUCCEED);
        return true;
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
