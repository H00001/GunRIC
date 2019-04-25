package netty.impl;

import netty.GunBootServer;

/**
 * create GunBootServer
 *
 * @author dosdrtt
 */
public class GunBootServerFactory {
    public static GunBootServer getInstance() {
        return new GunBootServerImpl();
    }
}
