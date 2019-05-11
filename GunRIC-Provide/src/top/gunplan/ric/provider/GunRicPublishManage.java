package top.gunplan.ric.provider;


import top.gunplan.ric.protocol.GunRicRegisterStatusProtocol;
import top.gunplan.ric.protocol.util.GunCLassPathUtil;
import top.gunplan.ric.provider.property.GunRICProvideProperty;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

/**
 * @nonconcurrent
 */
class GunRicPublishManage {
    private final GunRICProvideProperty ppt;
    /**
     *
     */

    private HashMap<Short, String> registerMapping = new HashMap<>();
    private BufferedReader reader;

    GunRicPublishManage(final GunRICProvideProperty ppt) {
        this.ppt = ppt;
        InputStream is = GunCLassPathUtil.getResFileAsStream(ppt.getPublishFileName());
        this.reader = new BufferedReader(new InputStreamReader(is));

    }

    boolean publishInterface() throws Exception {
        Socket ss = new Socket(ppt.getCenterAddr(), ppt.getCenterPort());
        final OutputStream os = ss.getOutputStream();
        final InputStream is = ss.getInputStream();
        if (publishRegister(ppt, os)) {
            return resolveResult(is);
        } else {
            return false;
        }
    }

    private boolean resolveResult(InputStream is) throws Exception {
        byte[] bt = new byte[BUFFER_LEN];
        GunRicRegisterStatusProtocol poto;
        int readied;
        int nowcount = 0;
        while ((readied = is.read(bt)) > 0) {
            poto = new GunRicRegisterStatusProtocol();
            byte[] bts = new byte[readied];
            System.arraycopy(bt, 0, bts, 0, readied);
            poto.unSerialize(bts);
            do {
                nowcount++;
                AbstractGunBaseLogUtil.debug(registerMapping.get((short) poto.getSerialnumber()) + " register succeed");
                poto = (GunRicRegisterStatusProtocol) poto.getNext();
            }
            while (poto != null);
            if (readied < BUFFER_LEN && nowcount == registerMapping.size()) {
                break;
            }
        }
        return true;
    }

    private boolean publishRegister(GunRICProvideProperty ppt, OutputStream os) {
        GunRicRegisterProtocol protocol = new GunRicRegisterProtocol();
        protocol.setPort(ppt.getServerLocalPort());
        Class<?> clazz;
        try {
            while ((clazz = getNextClass()) != null) {
                for (Method md : clazz.getMethods()) {
                    constructProtocol(clazz, md, protocol);
                    os.write(protocol.serialize());
                    protocol.clearParams();
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static final int BUFFER_LEN = 1024;

    private void constructProtocol(Class<?> clazz, Method md, GunRicRegisterProtocol protocol) {
        protocol.setInterfaceName(clazz.getName());
        protocol.setMethodName(md.getName());
        final int v = ((clazz.hashCode() + md.hashCode()) & 12768) + (int) (Math.random() * 1000);
        registerMapping.put((short) v, clazz.getName() + "." + md.getName());
        protocol.setSerialnumber(v);
        protocol.setParamount(md.getParameterCount());
        for (Class<?> tp : md.getParameterTypes()) {
            protocol.pushParamType(tp);
        }
    }

    private Class<?> getNextClass() throws IOException, ClassNotFoundException {
        String line = reader.readLine();
        return line != null ? Class.forName(line) : null;
    }
}
