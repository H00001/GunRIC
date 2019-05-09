package top.gunplan.ric.server;


import top.gunplan.protocol.GunRicRegisterStatusProtocol;
import top.gunplan.ric.server.property.GunRICProperty;
import top.gunplan.protocol.GunRicRegisterProtocol;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 *
 */
class GunRicPublishManage {
    static void publishInterface(final GunRICProperty ppt) throws IOException {
        Socket ss = new Socket(ppt.getCenterAddr(), ppt.getCenterPort());
        InputStream is = GunRicRegisterProtocol.class.getClassLoader().getResourceAsStream("publishInterface");
        assert is != null;
        GunRicRegisterProtocol protocol = new GunRicRegisterProtocol();
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
                }
            }
            byte[] bt = new byte[1024];

            int readle;
            GunRicRegisterStatusProtocol gp = new GunRicRegisterStatusProtocol();
            while ((readle = ss.getInputStream().read(bt)) >= 0) {
                byte[] bts = new byte[readle];
                System.arraycopy(bt, 0, bts, 0, readle);
                gp.unSerialize(bts);
                do {
                    AbstractGunBaseLogUtil.debug(gp.getSerialnumber() + " register succeed");
                    gp = (GunRicRegisterStatusProtocol) gp.getNext();
                }
                while (gp.getNext() != null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static void constructProtoclo(Class<?> clazz, Method md, GunRicRegisterProtocol protocol) {
        protocol.setInterfaceName(clazz.getName());
        protocol.setMethodName(md.getName());
        final int serialnum = (int) (System.currentTimeMillis() + (int) (Math.random() * 1000));
        protocol.setSerialnumber(serialnum);
        AbstractGunBaseLogUtil.debug(serialnum + " register");
        protocol.setParamlen(md.getParameterCount());
        for (Class<?> tp : md.getParameterTypes()) {
            protocol.pushParamType(tp);
        }
    }

}
