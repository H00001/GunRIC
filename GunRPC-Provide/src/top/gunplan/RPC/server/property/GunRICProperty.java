package top.gunplan.RPC.server.property;

import top.gunplan.netty.impl.propertys.GunProPerty;

public class GunRICProperty implements GunProPerty {
    private String scanPacket;
    private int port;
    private String center;

    public String getScanPacket() {
        return scanPacket;
    }

    public int getPort() {
        return port;
    }

    public String getCenter() {
        return center;
    }

    public void setScanPacket(String scanPacket) {
        this.scanPacket = scanPacket;
    }

    public GunRICProperty() {
    }
}
