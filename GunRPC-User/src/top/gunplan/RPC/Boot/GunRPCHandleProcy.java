package top.gunplan.RPC.Boot;


import top.gunplan.protocol.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @version 0.0.0.0
 * @since 0.0.0.0
 */

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

        GunRicInputProtocol protocl = new GunRicInputProtocol();
        protocl.setType(RicProtoclType.REQUEST);
        protocl.setCode(RicProtoclCode.SUCCEED);
        protocl.setInterfaceName(interfaceName);
        protocl.setMethodName(method.getName());
        if (args != null) {
            protocl.setParamLen((byte) args.length);
            for (Object arg : args) {
                protocl.pushParam(arg);
            }
        }
        out.write(protocl.serialize());

        byte[] b = new byte[2014];

        //
        int len = in.read(b);

        GunRicOutputProtocl rpc = (GunRicOutputProtocl) GunRicDividePacketManage.findPackage(b);
        if (rpc.getCode() == RicProtoclCode.FAIL) {
            System.out.println(rpc.getReturnValue().obj);
            return null;
        }
        if (rpc.getCode() == RicProtoclCode.SUCCEED) {
            return rpc.getReturnValue().obj;
        }
        return null;
    }
}
