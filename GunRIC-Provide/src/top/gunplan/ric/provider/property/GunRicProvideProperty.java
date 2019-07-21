package top.gunplan.ric.provider.property;


import top.gunplan.netty.GunProperty;
import top.gunplan.netty.anno.GunPropertyMap;
import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.utils.GunLogger;


import java.net.InetSocketAddress;

/**
 * GunRicProvideProperty
 *
 * @author dosdrtt
 */
@GunPropertyMap(name = "ric-provide")
public class GunRicProvideProperty implements GunProperty {
    private GunLogger logger = GunNettyContext.logger;
    private String scanPacket;
    private int localServerPort;
    private String centerAddr;
    private String publishFileName;
    private final static String DIV_FLAG = ",";
    private InetSocketAddress[] address;
    private String publishLocalIp;


    public String getPublishLocalIp() {
        return publishLocalIp;
    }

    public GunRicProvideProperty() {
        logger.setTAG(GunRicProvideProperty.class);
    }

    public InetSocketAddress[] getAddress() {
        return address;
    }


    public String getScanPacket() {
        return scanPacket;
    }


    public int getServerLocalPort() {
        return localServerPort;
    }

    public String getPublishFileName() {
        return publishFileName;
    }

    @Override
    public boolean isAvailable() {
        try {
            String[] servers;
            if (centerAddr.contains(DIV_FLAG)) {
                servers = centerAddr.split(DIV_FLAG);
            } else {
                servers = new String[]{centerAddr};
            }
            address = new InetSocketAddress[servers.length];
            for (int i = 0; i < servers.length; i++) {
                address[i] = new InetSocketAddress(servers[i].split("-")[0], Integer.parseInt(servers[i].split("-")[1]));
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean doRegex() {
        return isAvailable();
    }
}
