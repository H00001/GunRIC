package top.gunplan.ric.provider;


import top.gunplan.ric.protocol.GunAddressItem;
import top.gunplan.ric.protocol.GunRicRegisterStatusProtocol;

import top.gunplan.ric.protocol.SerizableCode;
import top.gunplan.ric.protocol.util.GunClassPathUtil;
import top.gunplan.ric.provider.property.GunRicProvideProperty;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.*;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;

/**
 * @nonconcurrent
 */
class GunRicPublishManage {
    private final GunRicProvideProperty ppt;
    /**
     *
     */

    private HashMap<Short, String> registerMapping = new HashMap<>();
    private BufferedReader reader;

    GunRicPublishManage(final GunRicProvideProperty ppt) {
        this.ppt = ppt;
        InputStream is = GunClassPathUtil.getResFileAsStream(ppt.getPublishFileName());
        this.reader = new BufferedReader(new InputStreamReader(is));

    }

    boolean publishInterface() throws Exception {
        InetSocketAddress[] addrs = ppt.getAddress();
        int succeedsum = 0;
        for (InetSocketAddress addr : addrs) {
            Socket ss = new Socket(addr.getHostString(), addr.getPort());
            if (publishRegister(ss.getOutputStream()) && resolveResult(ss.getInputStream())) {
                succeedsum++;
            }
            ss.close();
        }
        return succeedsum >= 1;
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
                AbstractGunBaseLogUtil.debug(registerMapping.get((short) poto.getSerialnumber()) + " register succeed", "[REGISTER]");
                poto = (GunRicRegisterStatusProtocol) poto.getNext();
            }
            while (poto != null);
            if (readied < BUFFER_LEN && nowcount == registerMapping.size()) {
                break;
            }
        }
        return true;
    }

    private boolean publishRegister(OutputStream os) {
        GunRicRegisterProtocol protocol = new GunRicRegisterProtocol();
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
            AbstractGunBaseLogUtil.error(e);
            return false;
        }
        return true;
    }

    private static final int BUFFER_LEN = 1024;


    private void constructProtocol(Class<?> clazz, Method md, GunRicRegisterProtocol protocol) {
        protocol.setInterfaceName(clazz.getName());
        protocol.setMethodName(md.getName());
        protocol.setItem(new GunAddressItem(ppt.getPublishLocalIp(), ppt.getServerLocalPort()));
        SerizableCode serizableCode = SerizableCode.newInstance();
        final int v = serizableCode.getSerizNum32();
        registerMapping.put((short) v, clazz.getName() + "." + md.getName());
        protocol.setSerialnumber(v);
        protocol.pushParamTypes(md.getParameterTypes());

    }

    private Class<?> getNextClass() throws IOException, ClassNotFoundException {
        String line = reader.readLine();
        return line != null ? Class.forName(line) : null;
    }
}
