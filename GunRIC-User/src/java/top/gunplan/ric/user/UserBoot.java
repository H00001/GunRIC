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

    public static <T> T iocobject(Class<T> clazz) throws IOException {
        GunRicUserProperty property = GunRicUserPropertyManageImpl.getProPerty();
        Socket so = new Socket(property.getAddress()[0].getHostString(), property.getAddress()[0].getPort());
        Class[] clazzs = {clazz};
        GunRicUserHandleProcy procy = new GunRicUserHandleProcy(clazz.getName(), so.getInputStream(), so.getOutputStream());
        T oc = null;
        try {
            oc = clazz.cast(Proxy.newProxyInstance(UserBoot.class.getClassLoader(), clazzs, procy));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return oc;
    }
}


