package top.gunplan.RPC.Boot;

import org.junit.jupiter.api.Test;
import top.gunplan.RPC.APIS.test.CalServicers;


import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.Socket;

final class BootCore {


    static <T> T IOCObject(Class<?> clazz) throws IOException {

        Socket so = new Socket("127.0.0.1", 8822);
    //    Socket so = null;
        Class[] clazzs = {clazz};
        GunRPCHandleProcy procy = new GunRPCHandleProcy(clazz.getName(), so.getInputStream(), so.getOutputStream());
        // so.close();
        return (T) Proxy.newProxyInstance(BootCore.class.getClassLoader(), clazzs, procy);
    }
}

class sdd {
    @Test
    void dotest() throws IOException {
        CalServicers hs = BootCore.IOCObject(CalServicers.class);
        System.out.println(hs.add(1024, 6));
        //   System.out.println(hs.sub(1024, 5));
        //   System.out.println(hs.multiplication(1024, 5));

    }
}
