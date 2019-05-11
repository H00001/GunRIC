package top.gunplan.RPC.Boot;


import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author dosdrtt
 */
public final class BootCore {

    public static <T> T iocobject(Class<T> clazz) throws IOException {
        Socket so = new Socket("127.0.0.1", 8822);
        // Socket so = new Socket();
        Class[] clazzs = {clazz};
        GunRPCHandleProcy procy = new GunRPCHandleProcy(clazz.getName(), so.getInputStream(), so.getOutputStream());
        Object oc = null;
        try {
            oc = Proxy.newProxyInstance(BootCore.class.getClassLoader(), clazzs, procy);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return clazz.cast(oc);
    }
}


