package top.gunplan.ric.center.property;

import top.gunplan.netty.GunProperty;
import top.gunplan.netty.anno.GunPropertyMap;


/**
 * @author dosdrtt
 * @see GunProperty
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
