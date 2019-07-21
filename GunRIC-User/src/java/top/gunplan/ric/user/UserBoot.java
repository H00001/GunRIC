package top.gunplan.ric.user;


import top.gunplan.netty.common.GunNettyContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author dosdrtt
 */
public final class UserBoot {
    static {
        GunRicUserPropertyManageImpl.initProperty();
        //  GunRicUserConnectionFactoryImpl.scan();
    }

    static <T> T iocObject(Class<T> clazz) {
        Class[] clazzi = {clazz};
        InvocationHandler proxy = new GunRicUserHandleProxy();
        T oc = null;
        try {
            oc = clazz.cast(Proxy.newProxyInstance(UserBoot.class.getClassLoader(), clazzi, proxy));
        } catch (Exception exp) {
            GunNettyContext.logger.error(exp);
        }
        return oc;
    }
}


