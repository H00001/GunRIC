package top.gunplan.ric.user;


import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author dosdrtt
 */
public final class UserBoot {
    static {
        GunRicUserPropertyManageImpl.initProperty();
        //  GunRicUserConnectionFactory.scan();
    }

    public static <T> T iocObject(Class<T> clazz) throws IOException {
        Class[] clazzi = {clazz};
        GunRicUserHandleProxy proxy = new GunRicUserHandleProxy();
        T oc = null;
        try {
            oc = clazz.cast(Proxy.newProxyInstance(UserBoot.class.getClassLoader(), clazzi, proxy));
        } catch (Exception exp) {
            AbstractGunBaseLogUtil.error(exp);
        }
        return oc;
    }
}


