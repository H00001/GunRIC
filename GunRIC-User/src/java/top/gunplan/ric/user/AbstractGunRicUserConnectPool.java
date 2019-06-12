package top.gunplan.ric.user;

import top.gunplan.ric.common.GunRicPool;


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


    /**
     * available count
     *
     * @return
     */
    @Override
    public abstract int availableCount();

    /**
     * allCount
     *
     * @return all of count
     */
    @Override
    public abstract int allCount();

}
