package top.gunplan.ric.center.property;

import top.gunplan.netty.anno.GunPropertyMap;
import top.gunplan.netty.impl.propertys.GunProperty;

/**
 * @author dosdrtt
 * @see top.gunplan.netty.impl.propertys.GunProperty
 */
@GunPropertyMap(name = "ric-center-services")
public class GunRicCenterServicesProperty implements GunProperty {
    private String redisaddr;
    private String servicespath;
    private String redispwd;

    public String getRedispwd() {
        return redispwd;
    }

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
