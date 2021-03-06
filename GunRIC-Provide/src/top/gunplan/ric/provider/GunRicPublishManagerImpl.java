

package top.gunplan.ric.provider;


import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.ric.common.AbstractGunRicCommonProtocolSocket;
import top.gunplan.ric.common.GunRicUserConnectionFactoryImpl;
import top.gunplan.ric.protocol.GunAddressItem4;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;
import top.gunplan.ric.protocol.GunRicRegisterStatusProtocol;
import top.gunplan.ric.protocol.SerizableCode;
import top.gunplan.ric.protocol.util.GunClassPathUtil;
import top.gunplan.ric.provider.property.GunRicProvideProperty;
import top.gunplan.utils.GunLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @nonconcurrent
 */
class GunRicPublishManagerImpl implements GunRicPublishManager {
    private static GunLogger logger = GunNettyContext.logger.setTAG(GunRicPublishManagerImpl.class);
    private final GunRicProvideProperty ppt;
    /**
     *
     */

    private Map<Short, String> registerMapping = new HashMap<>();
    private BufferedReader reader;

    GunRicPublishManagerImpl(final GunRicProvideProperty ppt) {
        this.ppt = ppt;
        InputStream is = GunClassPathUtil.getResFileAsStream(ppt.getPublishFileName());
        this.reader = new BufferedReader(new InputStreamReader(is));

    }

    @Override
    public boolean publishInterface() throws IOException, ReflectiveOperationException {
        InetSocketAddress[] addrs = ppt.getAddress();
        int succeedsum = 0;
        for (InetSocketAddress addr : addrs) {
            AbstractGunRicCommonProtocolSocket p = GunRicUserConnectionFactoryImpl.newSocket(addr);
            if (publishRegister(p) && resolveResult(p)) {
                succeedsum++;
            }
            p.setNoUsed();
        }
        return succeedsum >= 1;
    }

    private boolean resolveResult(AbstractGunRicCommonProtocolSocket is) throws ReflectiveOperationException, IOException {
        int nowcount = 0;
        while (nowcount < registerMapping.size()) {
            GunRicRegisterStatusProtocol protocol = is.receiveProtocol(GunRicRegisterStatusProtocol.class);
            do {
                nowcount++;
                logger.debug(registerMapping.get((short) protocol.serializeNumber()) + " register succeed", "[REGISTER]");
                protocol = (GunRicRegisterStatusProtocol) protocol.next();
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
        } catch (ReflectiveOperationException | IOException e) {
            if (e instanceof IOException) {
                logger.error(e.getMessage(), "cannot output please check Socket");
            } else {
                logger.error(e.getMessage(), "class error");
            }
            return false;
        }
        return true;
    }

    private void constructProtocol(Class<?> clazz, Method md, GunRicRegisterProtocol protocol) {
        protocol.setINameMName(md);
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

