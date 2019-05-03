package top.gunplan.RPC.server;


import top.gunplan.RPC.server.property.GunRICProperty;
import top.gunplan.protocol.GunRICRegisterProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.Socket;

class GunRPCPublishManage {
    static void publishInterface(final GunRICProperty ppt) throws IOException {
        Socket ss = new Socket(ppt.getCenterAddr(), ppt.getCenterPort());
        InputStream is = GunRICRegisterProtocol.class.getClassLoader().getResourceAsStream("publishInterface");
        assert is != null;
        GunRICRegisterProtocol protocol = new GunRICRegisterProtocol();
        protocol.setPort(ppt.getServerLocalPort());
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                Class<?> clazz = Class.forName(line);
                for (Method md : clazz.getMethods()) {
                    constructProtoclo(clazz, md, protocol);
                    ss.getOutputStream().write(protocol.serialize());
                    protocol.clearParames();
                    Thread.sleep(1200);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    private static void constructProtoclo(Class<?> clazz, Method md, GunRICRegisterProtocol protocol) {
        protocol.setInterfaceName(clazz.getName());
        protocol.setMethodName(md.getName());
        protocol.setParamlen(md.getParameterCount());
        for (Class<?> tp : md.getParameterTypes()) {
            protocol.pushParamType(tp);
        }
    }

}
