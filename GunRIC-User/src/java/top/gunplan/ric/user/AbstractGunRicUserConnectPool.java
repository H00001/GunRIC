package top.gunplan.ric.user;

import top.gunplan.ric.common.tct.GunRicPool;

/**
 * @author dosdrtt
 * @time 1557659415
 * @see GunRicPool
 */
public abstract class AbstractGunRicUserConnectPool implements GunRicPool {

    private final String address;
    private final int port;
    private final int count;

    AbstractGunRicUserConnectPool(String address, int port, int count) {
        this.address = address;
        this.count = count;
        this.port = port;
    }

    public int getCount() {
        return count;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public abstract int avaliableCount();

    @Override
    public abstract int allCount();

}
