package top.gunplan.RPC.Boot;


import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.Socket;

public final class BootCore {

    public static <T> T IOCObject(Class<?> clazz) throws IOException {
         Socket so = new Socket("127.0.0.1", 8822);
       // Socket so = new Socket();
        Class[] clazzs = {clazz};
          GunRPCHandleProcy procy = new GunRPCHandleProcy(clazz.getName(), so.getInputStream(), so.getOutputStream());
        // so.close();
   //     GunRPCHandleProcy procy = new GunRPCHandleProcy(clazz.getName(), null, null);
        return (T) Proxy.newProxyInstance(BootCore.class.getClassLoader(), clazzs, procy);
    }
}


