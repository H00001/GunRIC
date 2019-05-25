package top.gunplan.ric.provider;

import top.gunplan.ric.apis.test.anno.GunUseImpl;
import top.gunplan.netty.GunException;

import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.lang.reflect.Method;
import java.nio.channels.SocketChannel;

import static top.gunplan.ric.protocol.RicProtocolCode.FAIL;
import static top.gunplan.ric.protocol.RicProtocolType.RESPONSE;


/**
 * @author dosdrtt
 */
public class GunStdRicHandle implements GunRicCommonRealDeal {

    @Override
    public AbstractGunRicProtocol dealDataEvent(AbstractGunRicProtocol gunNetInputInterface) {
        AbstractGunRicExecuteProtocol.ParamHelper help = new AbstractGunRicExecuteProtocol.ParamHelper();
        final GunRicOutputProtocol o = new GunRicOutputProtocol();
        final GunRicInputProtocol i = ((GunRicInputProtocol) gunNetInputInterface);
        o.setReturnValue(help);
        o.setType(RESPONSE);
        o.setSerialnumber(i.getSerialnumber());
        try {
            if (invokeMethod(help, o, i)) {
                return o;
            }
        } catch (ReflectiveOperationException e) {
            help.setObj(e.getClass().getSimpleName() + ":" + e.getLocalizedMessage());
            o.setCode(FAIL);
        } catch (Exception exp) {
            AbstractGunBaseLogUtil.error(exp);
        }
        return o;
    }

    private boolean invokeMethod(AbstractGunRicExecuteProtocol.ParamHelper help, GunRicOutputProtocol outputpol, GunRicInputProtocol inputpol) throws Exception {
        Class<?> inst = Class.forName(inputpol.gIN());
        Object rpcService = Class.forName(inst.getAnnotation(GunUseImpl.class).impl()).getDeclaredConstructor().newInstance();
        Method realmd = inst.getMethod(inputpol.gMN(), inputpol.getParamTypeList());
        if (realmd == null) {
            outputpol.setCode(FAIL);
            help.setObj("method not found ");
            AbstractGunBaseLogUtil.error(inputpol.gMN(), "method not found", "[PROVIDE]");
            return false;
        }
        help.setObj(inputpol.getParamleng() == 0 ? realmd.invoke(rpcService) : realmd.invoke(rpcService, inputpol.getParameters()));
        outputpol.setCode(RicProtocolCode.SUCCEED);
        return true;
    }


}
