package top.gunplan.RPC.Boot;


import top.gunplan.protocol.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class GunRPCHandleProcy implements InvocationHandler {
    private final String interfaceName;
    private final InputStream in;
    private final OutputStream out;

    GunRPCHandleProcy(String interfaceName, InputStream address, OutputStream port) {
        this.interfaceName = interfaceName;
        this.in = address;
        this.out = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        GunRPCInputProtocl protocl = new GunRPCInputProtocl();
        protocl.setType(RPCProtoclType.REQUEST);
        protocl.setCode(RPCProtoclCode.SUCCEED);
        protocl.setInterfaceName(interfaceName);
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
        byte[] bc = protocl.serialize();
        out.write(bc);
        byte[] b = new byte[2014];
        //
        int len = in.read(b);
        GunRPCOutputProtocl rpc = GunRPCDividePacketManage.findPackage(b);
        if (rpc.getCode()== RPCProtoclCode.FAIL)
        {
            return -1;
        }
        return rpc.getReturnValue();
    }
}
