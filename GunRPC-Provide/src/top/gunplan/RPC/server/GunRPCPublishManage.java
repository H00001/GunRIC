package top.gunplan.RPC.server;

import top.gunplan.RPC.server.property.GunRICProperty;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.protocol.GunRICRegisterProtocol;

import java.io.IOException;
import java.net.Socket;

public class GunRPCPublishManage {
    public static void publishInterface() {
       final String packet = ((GunRICProperty) GunNettyPropertyManagerImpl.getProperty("ric-provide")).getScanPacket();
        GunRICRegisterProtocol protocol = new GunRICRegisterProtocol();
        protocol.setInterfaceName("top.gunplan.RPC.APIS.test.Cal.CalServicers");
        protocol.setMethodName("intAdd");

        protocol.setParamlen(2);
        protocol.pushParamType(int.class);
        protocol.pushParamType(int.class);
        protocol.setPort(8822);
        System.out.println("dd");
        try {
            Socket ss = new Socket("127.0.0.1",8855);
            ss.getOutputStream().write(protocol.serialize());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
