package top.gunplan.RPC.server.property;

import top.gunplan.netty.impl.propertys.GunProPerty;

public class GunRICProperty implements GunProPerty {
    private String scanPacket;

    public String getScanPacket() {
        return scanPacket;
    }

    public void setScanPacket(String scanPacket) {
        this.scanPacket = scanPacket;
    }

    public GunRICProperty() {
    }
}
