package top.gunplan.ric.provider.property;


import top.gunplan.netty.impl.propertys.GunProperty;

/**
 * GunRICProvideProperty
 * @author dosdrtt
 */
public class GunRICProvideProperty implements GunProperty {
    private String scanPacket;
    private int centerPort;
    private int localServerPort;
    private String centerAddr;
    private String publishFileName;

    public GunRICProvideProperty() {
    }


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

    public String getPublishFileName() {
        return publishFileName;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
