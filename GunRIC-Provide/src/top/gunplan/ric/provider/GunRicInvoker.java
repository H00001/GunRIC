package top.gunplan.ric.provider;

import top.gunplan.ric.protocol.AbstractGunRicExecuteProtocol;
import top.gunplan.ric.protocol.AbstractGunRicProtocol;

/**
 * GunRicInvoker
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-29 19:44
 */
public interface GunRicInvoker<P extends AbstractGunRicProtocol> {
    AbstractGunRicExecuteProtocol.ParamHelper invokeMethod(P protocol) throws ReflectiveOperationException;
}
