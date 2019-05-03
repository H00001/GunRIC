package top.gunplan.RPC.server;

import top.gunplan.RPC.APIS.test.CalServicers;
import top.gunplan.RPC.server.property.GunRICProperty;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.protocol.GunRICRegisterProtocol;

import java.lang.reflect.Method;
import java.net.Socket;

public class GunRPCPublishManage {
    public static void publishInterface() {
        GunRICProperty ppt = GunNettyPropertyManagerImpl.getProperty("ric-provide");
        final String packet = ppt.getScanPacket();
        GunRICRegisterProtocol protocol = new GunRICRegisterProtocol();
        protocol.setInterfaceName("top.gunplan.RPC.APIS.test.Cal.CalServicers");

        protocol.setPort(8822);
        Method[] mds = CalServicers.class.getMethods();
        try {
            Socket ss = new Socket(ppt.getCenter(), ppt.getPort());
            for (Method md : mds) {
                protocol.setMethodName(md.getName());
                protocol.setParamlen(md.getParameterCount());
                for (Class<?> tp : md.getParameterTypes()) {
                    protocol.pushParamType(tp);
                }

                ss.getOutputStream().write(protocol.serialize());
                Thread.sleep(1000);
                protocol.clearParames();
            }
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
