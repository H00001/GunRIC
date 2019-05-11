package top.gunplan.ric.provider;


import top.gunplan.ric.protocol.GunRicRegisterStatusProtocol;
import top.gunplan.ric.provider.property.GunRICProperty;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;
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
    static boolean publishInterface(final GunRICProperty ppt) throws IOException {
        Socket ss = new Socket(ppt.getCenterAddr(), ppt.getCenterPort());
        InputStream is = GunRicRegisterProtocol.class.getClassLoader().getResourceAsStream("publishInterface");
        assert is != null;
        GunRicRegisterProtocol protocol = new GunRicRegisterProtocol();
        protocol.setPort(ppt.getServerLocalPort());
        String line;
        try {
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                while ((line = reader.readLine()) != null) {
                    Class<?> clazz = Class.forName(line);
                    for (Method md : clazz.getMethods()) {
                        constructProtocol(clazz, md, protocol);
                        ss.getOutputStream().write(protocol.serialize());
                        protocol.clearParams();
                    }
                }
            }
            byte[] bt = new byte[BUFFER_LEN];
            int readied;
            GunRicRegisterStatusProtocol gp;
            while ((readied = ss.getInputStream().read(bt)) > 0) {
                gp = new GunRicRegisterStatusProtocol();
                byte[] bts = new byte[readied];
                System.arraycopy(bt, 0, bts, 0, readied);
                gp.unSerialize(bts);
                do {
                    AbstractGunBaseLogUtil.debug(gp.getSerialnumber() + " register succeed");
                    gp = (GunRicRegisterStatusProtocol) gp.getNext();
                }
                while (gp != null);
                if (readied < BUFFER_LEN) {
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    private static void constructProtocol(Class<?> clazz, Method md, GunRicRegisterProtocol protocol) {
        protocol.setInterfaceName(clazz.getName());
        protocol.setMethodName(md.getName());
        final int v = ((clazz.hashCode() + md.hashCode()) & 12768) + (int) (Math.random() * 1000);
        protocol.setSerialnumber(v);
        AbstractGunBaseLogUtil.debug(clazz.getName() + "." + md.getName() + ":" + v + " register");
        protocol.setParamount(md.getParameterCount());
        for (Class<?> tp : md.getParameterTypes()) {
            protocol.pushParamType(tp);
        }
    }

    private static final int BUFFER_LEN = 1024;

}
