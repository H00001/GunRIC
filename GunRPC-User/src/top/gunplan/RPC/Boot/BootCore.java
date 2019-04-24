package top.gunplan.RPC.Boot;

import org.junit.jupiter.api.Test;
import top.gunplan.RPC.APIS.test.HelloServices;
import protocol.GunRPCInputProtocl;
import protocol.GunRPCOutputProtocl;
import protocol.RPCProtoclCode;
import protocol.RPCProtoclType;

import java.lang.reflect.Proxy;
import java.net.Socket;

public final class BootCore {
    public static Object IOCObject(Class<?> clazz) {
        Class[] clazzs = {clazz};
        return Proxy.newProxyInstance(BootCore.class.getClassLoader(), clazzs, (proxy, method, args) -> {
            GunRPCInputProtocl protocl = new GunRPCInputProtocl();
            protocl.setType(RPCProtoclType.REQUEST);
            protocl.setCode(RPCProtoclCode.SUCCEED);
            protocl.setInterfaceName(clazz.getName());
            protocl.setMethodName(method.getName());
            if (args != null) {
                for (Object arg : args) {
                    if (arg instanceof Integer) {
                        protocl.poshParam((Integer) arg);
                    } else if (arg instanceof String) {
                        protocl.poshParam((String) arg);
                    }
                }
            }
            Socket so = new Socket("127.0.0.1", 8822);
            so.getOutputStream().write(protocl.serialize());
            byte[] b = new byte[2014];
            Thread.sleep(1000);
            so.getInputStream().read(b);
            GunRPCOutputProtocl rpt = new GunRPCOutputProtocl();
            rpt.unSerialize(b);
            return rpt.getReturnValue();
        });
    }
}

class sdd {
    @Test
    void dotest() {
        HelloServices hs = (HelloServices) BootCore.IOCObject(HelloServices.class);
        System.out.println(hs.sayGoodBay("li ming"));
    }
}
