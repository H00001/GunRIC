package top.gunplan.ric.user;


import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author dosdrtt
 */
public final class UserBoot {
    static {
        GunRicUserPropertyManageImpl.initProperty();
    }
    public static <T> T iocObject(Class<T> clazz) throws IOException {

        Class[] clazzs = {clazz};
        GunRicUserHandleProxy procxy = new GunRicUserHandleProxy();
        T oc = null;
        try {
            oc = clazz.cast(Proxy.newProxyInstance(UserBoot.class.getClassLoader(), clazzs, procxy));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return oc;
    }
}


