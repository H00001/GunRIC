package top.gunplan.ric.provider;

import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.ric.apis.test.anno.GunUseImpl;
import top.gunplan.ric.protocol.AbstractGunRicExecuteProtocol;
import top.gunplan.ric.protocol.GunRicCommonRealDeal;
import top.gunplan.ric.protocol.GunRicOutputProtocol;
import top.gunplan.ric.stand.GunRicInvokeReqStand;
import top.gunplan.ric.stand.GunRicInvokeResStand;
import top.gunplan.utils.GunLogger;

import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import static top.gunplan.ric.protocol.RicProtocolCode.FAIL;
import static top.gunplan.ric.provider.GunRicProviderException.GunRicProviderErrorType.INVOKE_ERROR;


/**
 * @author dosdrtt
 */
public class GunStdRicProviderHandle implements GunRicInvoker<GunRicInvokeReqStand>, GunRicCommonRealDeal<GunRicInvokeReqStand, GunRicInvokeResStand> {
    private static GunLogger logger = GunNettyContext.logger.setTAG(GunStdRicProviderHandle.class);

    @Override
    public GunRicInvokeResStand dealDataEvent(final GunRicInvokeReqStand i) {
        AbstractGunRicExecuteProtocol.ParamHelper helper = new AbstractGunRicExecuteProtocol.ParamHelper();
        final GunRicInvokeResStand o = new GunRicOutputProtocol(i);
        try {
            helper = invokeMethod(i);
            o.setResult(helper);
        } catch (ReflectiveOperationException e) {
            helper.setObj(e.getClass().getSimpleName() + ":" + e.getMessage());
            logger.error(e);
            o.setCode(FAIL);
        }
        return o;

    }

    @Override
    public AbstractGunRicExecuteProtocol.ParamHelper invokeMethod(GunRicInvokeReqStand inputProtocol) throws ReflectiveOperationException {
        AbstractGunRicExecuteProtocol.ParamHelper help = new AbstractGunRicExecuteProtocol.ParamHelper();
        MethodType mt = MethodType.methodType(null, inputProtocol.paramTypes()[0], inputProtocol.paramTypes());

        Class<?> inst = Class.forName(inputProtocol.interfaceName());
        Object rpcService = Class.forName(inst.getAnnotation(GunUseImpl.class).impl()).getDeclaredConstructor().newInstance();
        Method instMethod = inst.getMethod(inputProtocol.methodName(), inputProtocol.paramTypes());
        if (instMethod == null) {
            help.setObj("method not found");
            logger.error(inputProtocol.methodName(), "method not found", "[PROVIDE]");
            throw new GunRicProviderException("method not found", INVOKE_ERROR);
        }
        help.setObj(inputProtocol.paramLength() == 0 ? instMethod.invoke(rpcService) : instMethod.invoke(rpcService, inputProtocol.parameters()));
        return help;
    }


}
