package top.gunplan.RPC.Boot;


import top.gunplan.netty.impl.example.GunOutputFilterChecker;
import top.gunplan.netty.protocol.GunRPCInputProtocl;
import top.gunplan.netty.protocol.GunRPCOutputProtocl;
import top.gunplan.netty.protocol.RPCProtoclCode;
import top.gunplan.netty.protocol.RPCProtoclType;

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
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof Integer) {
                        protocl.poshParam((Integer) args[i]);
                    } else if (args[i] instanceof String) {
                        protocl.poshParam((String) args[i]);
                    }
                }
            }
            Socket so = new Socket("127.0.0.1",8822);
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
