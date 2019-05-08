package top.gunplan.RPC.Boot;


import top.gunplan.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

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
        GunRicInputProtocol input = new GunRicInputProtocol();
        input.setType(RicProtocolType.REQUEST);
        input.setCode(RicProtoclCode.SUCCEED);
        input.setInterfaceName(interfaceName);
        input.setSerialnumber((int) System.currentTimeMillis());
        input.setMethodName(method.getName());
        if (args != null) {
            input.setParamLen((byte) args.length);
            for (Object arg : args) {
                if (!input.pushParam(arg)) {
                    AbstractGunBaseLogUtil.error("some thing happened");
                    return null;
                }
            }
        }
        out.write(input.serialize());
        byte[] b = new byte[2014];
        int len = in.read(b);

        GunRicOutputProtocol output = (GunRicOutputProtocol) GunRicDividePacketManage.findPackage(b);
        if (output.getCode() == RicProtoclCode.FAIL) {
            System.out.println(output.getReturnValue().obj);
            return null;
        }
        if (output.getCode() == RicProtoclCode.SUCCEED) {
            if (output.getSerialnumber() == input.getSerialnumber()) {
                return output.getReturnValue().obj;
            }
        }
        return null;
    }
}
