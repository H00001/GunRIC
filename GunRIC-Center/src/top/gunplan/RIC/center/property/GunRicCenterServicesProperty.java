package top.gunplan.RIC.center.property;

import top.gunplan.netty.impl.propertys.GunProperty;

/**
 * @author dosdrtt
 * @see top.gunplan.netty.impl.propertys.GunProperty
 */
public class GunRicCenterServicesProperty implements GunProperty {
    private String redisaddr;
    private String servicespath;

    public String getRedisaddr() {
        return redisaddr;
    }

    public String getServicespath() {
        return servicespath;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
