package top.gunplan.ric.provider;

import top.gunplan.ric.apis.test.anno.GunUseImpl;
import top.gunplan.ric.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.lang.reflect.Method;


import static top.gunplan.ric.protocol.RicProtocolCode.FAIL;
import static top.gunplan.ric.provider.GunRicProviderException.GunRicProviderErrorType.INVOKE_ERROR;


/**
 * @author dosdrtt
 */
public class GunStdRicProviderHandle implements GunRicInvoker<GunRicInputProtocol>, GunRicCommonRealDeal<GunRicInputProtocol, GunRicOutputProtocol> {

    @Override
    public GunRicOutputProtocol dealDataEvent(final GunRicInputProtocol i) {
        AbstractGunRicExecuteProtocol.ParamHelper helper = new AbstractGunRicExecuteProtocol.ParamHelper();
        final GunRicOutputProtocol o = new GunRicOutputProtocol(i);
        try {
            helper = invokeMethod(i);
            o.setReturnValue(helper);
        } catch (ReflectiveOperationException e) {
            helper.setObj(e.getClass().getSimpleName() + ":" + e.getMessage());
            AbstractGunBaseLogUtil.error(e);
            o.setCode(FAIL);
        }
        return o;

    }

    @Override
    public AbstractGunRicExecuteProtocol.ParamHelper invokeMethod(GunRicInputProtocol inputProtocol) throws ReflectiveOperationException {
        AbstractGunRicExecuteProtocol.ParamHelper help = new AbstractGunRicExecuteProtocol.ParamHelper();
        Class<?> inst = Class.forName(inputProtocol.gIN());
        Object rpcService = Class.forName(inst.getAnnotation(GunUseImpl.class).impl()).getDeclaredConstructor().newInstance();
        Method instMethod = inst.getMethod(inputProtocol.gMN(), inputProtocol.getParamTypeList());
        if (instMethod == null) {
            help.setObj("method not found");
            AbstractGunBaseLogUtil.error(inputProtocol.gMN(), "method not found", "[PROVIDE]");
            throw new GunRicProviderException("method not found", INVOKE_ERROR);
        }
        help.setObj(inputProtocol.getParamLen() == 0 ? instMethod.invoke(rpcService) : instMethod.invoke(rpcService, inputProtocol.getParameters()));
        return help;
    }


}
