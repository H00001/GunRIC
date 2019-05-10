package top.gunplan.ric.provider.property;


import top.gunplan.netty.impl.propertys.GunProperty;

/**
 * @author dosdrtt
 */
public class GunRICProperty implements GunProperty {
    private String scanPacket;
    private int centerPort;
    private int localServerPort;
    private String centerAddr;

    public String getScanPacket() {
        return scanPacket;
    }

    public int getCenterPort() {
        return centerPort;
    }

    public int getServerLocalPort() {
        return localServerPort;
    }

    public String getCenterAddr() {
        return centerAddr;
    }

    public GunRICProperty() {
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
