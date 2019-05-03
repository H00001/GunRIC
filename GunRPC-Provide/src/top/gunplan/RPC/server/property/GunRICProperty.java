package top.gunplan.RPC.server.property;

import top.gunplan.netty.impl.propertys.GunProPerty;

public class GunRICProperty implements GunProPerty {
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
}
