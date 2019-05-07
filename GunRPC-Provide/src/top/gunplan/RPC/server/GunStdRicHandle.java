package top.gunplan.RPC.server;

import top.gunplan.RPC.APIS.test.anno.GunUseImpl;
import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.protocol.*;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.lang.reflect.Method;
import java.nio.channels.SocketChannel;

/**
 * @author dosdrtt
 */
public class GunStdRicHandle implements GunNettyHandle {

    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface gunNetInputInterface) {
        AbstractGunRPCExecuteProtocol.ParamHelper help = new AbstractGunRPCExecuteProtocol.ParamHelper();
        final GunRPCOutputProtocl o = new GunRPCOutputProtocl();
        final GunRPCInputProtocl i = ((GunRPCInputProtocl) gunNetInputInterface);
        o.setReturnValue(help);
        o.setType(RPCProtoclType.RESPONSE);
        try {
            if (invokeMethod(help, o, i)) {
                return o;
            }
        } catch (ReflectiveOperationException e) {
            help.setObj(e.getClass().getSimpleName() + ":" + e.getLocalizedMessage());
            o.setCode(RPCProtoclCode.FAIL);
        } catch (Exception exp) {
            this.dealExceptionEvent(exp);
        }
        return o;
    }

    private boolean invokeMethod(AbstractGunRPCExecuteProtocol.ParamHelper help, GunRPCOutputProtocl outputpol, GunRPCInputProtocl inputpol) throws Exception {
        Class<?> inst = Class.forName(inputpol.getInterfaceName());
        Object rpcService = Class.forName(inst.getAnnotation(GunUseImpl.class).impl()).newInstance();
        Method realmd = inst.getMethod(inputpol.getMethodName(), inputpol.getParamTypeList());
        if (realmd == null) {
            outputpol.setCode(RPCProtoclCode.FAIL);
            help.setObj("method not found ");
            AbstractGunBaseLogUtil.error(inputpol.getMethodName(), "method not found", "[PROVIDE]");
            return false;
        }
        help.setObj(inputpol.getParamleng() == 0 ? realmd.invoke(rpcService) : realmd.invoke(rpcService, inputpol.getParameters()));
        outputpol.setCode(RPCProtoclCode.SUCCEED);
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
        AbstractGunBaseLogUtil.error(e.getMessage());
    }
}
