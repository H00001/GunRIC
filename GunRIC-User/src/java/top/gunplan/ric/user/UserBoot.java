package top.gunplan.ric.user;


import top.gunplan.ric.protocol.GunRicGetAddressProtocol;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author dosdrtt
 */
public final class UserBoot {
    private static GunRicUserProperty property;

    static {
        GunRicUserPropertyManageImpl.initProperty();
        try {
            property = GunRicUserPropertyManageImpl.getProPerty();
            UserBoot.getServerAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getServerAddress() throws IOException {
        Socket so = new Socket(property.getAddress()[0].getHostString(), property.getAddress()[0].getPort());
        GunRicGetAddressProtocol poctol = new GunRicGetAddressProtocol();
        poctol.setInterfaceName("top.gunplan.ric.apis.test.Find");
        poctol.setMethodName("findByBinaryDivide");
        poctol.setParamcount(2);
        poctol.pushParamType(int[].class);
        poctol.pushParamType(int.class);
        poctol.setSerialnumber(1);
        so.getOutputStream().write(poctol.serialize());
        byte[] buffer = new byte[1024];
        so.getInputStream().read(buffer);
        GunRicRespAddressProtocol protocol = new GunRicRespAddressProtocol();
        poctol.unSerialize(buffer);
    }

    public static <T> T iocObject(Class<T> clazz) throws IOException {

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


