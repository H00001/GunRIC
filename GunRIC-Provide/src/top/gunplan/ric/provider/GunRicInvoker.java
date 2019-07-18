package top.gunplan.ric.provider;

import top.gunplan.ric.protocol.AbstractGunRicExecuteProtocol;
import top.gunplan.ric.protocol.AbstractGunRicProtocol;
import top.gunplan.ric.stand.GunRicBaseStand;

/**
 * GunRicInvoker
 *
 * @author frank albert
 * @version 0.0.0.2
 * @date 2019-06-29 19:44
 */
public interface GunRicInvoker<P extends GunRicBaseStand> {
    AbstractGunRicExecuteProtocol.ParamHelper invokeMethod(P protocol) throws ReflectiveOperationException;
}
