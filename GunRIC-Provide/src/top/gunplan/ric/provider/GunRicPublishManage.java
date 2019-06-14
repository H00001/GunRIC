

package top.gunplan.ric.provider;


import top.gunplan.ric.common.AbstractGunRicCommonProtocolSocket;
import top.gunplan.ric.common.GunRicUserConnectionFactory;
import top.gunplan.ric.protocol.GunAddressItem4;
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
import java.util.Map;

/**
 * @nonconcurrent
 */
class GunRicPublishManage {
    private final GunRicProvideProperty ppt;
    /**
     *
     */

    private Map<Short, String> registerMapping = new HashMap<>();
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
            AbstractGunRicCommonProtocolSocket p = GunRicUserConnectionFactory.newSocket(addr);
            if (publishRegister(p) && resolveResult(p)) {
                succeedsum++;
            }
            p.setNoUsed();
        }
        return succeedsum >= 1;
    }

    private boolean resolveResult(AbstractGunRicCommonProtocolSocket is) throws Exception {
        int nowcount = 0;
        while (nowcount < registerMapping.size()) {
            GunRicRegisterStatusProtocol protocol = is.receiveProtocol(GunRicRegisterStatusProtocol.class);
            do {
                nowcount++;
                AbstractGunBaseLogUtil.debug(registerMapping.get((short) protocol.getSerialnumber()) + " register succeed", "[REGISTER]");
                protocol = (GunRicRegisterStatusProtocol) protocol.getNext();
            }
            while (protocol != null);
        }
        return true;
    }

    private boolean publishRegister(AbstractGunRicCommonProtocolSocket os) {
        GunRicRegisterProtocol protocol = new GunRicRegisterProtocol();
        Class<?> clazz;
        try {
            while ((clazz = getNextClass()) != null) {
                for (Method md : clazz.getMethods()) {
                    constructProtocol(clazz, md, protocol);
                    os.sendProtocol(protocol);
                    protocol.clearParams();
                }
            }
        } catch (Exception e) {
            AbstractGunBaseLogUtil.error(e);
            return false;
        }
        return true;
    }

    private void constructProtocol(Class<?> clazz, Method md, GunRicRegisterProtocol protocol) {
        protocol.setInameMname(md);
        protocol.setItem(new GunAddressItem4(ppt.getPublishLocalIp(), ppt.getServerLocalPort()));
        SerizableCode serizableCode = SerizableCode.newInstance();
        final int v = serizableCode.getSerialNum32();
        registerMapping.put((short) v, clazz.getName() + "." + md.getName());
        protocol.setSerialnumber(v);
        protocol.pushParamTypes(md.getParameterTypes());
    }

    private Class<?> getNextClass() throws IOException, ClassNotFoundException {
        String line = reader.readLine();
        return line != null ? Class.forName(line) : null;
    }
}

