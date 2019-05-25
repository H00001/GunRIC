package top.gunplan.ric.center.property;

import top.gunplan.netty.impl.propertys.GunProperty;

/**
 * this is a property object
 * stand is {@link GunProperty}
 *
 * @author dosdrtt
 * @date 2019/05/22
 * @see top.gunplan.netty.impl.propertys.GunProperty
 */
public class GunRicCenterServiceUtilProperty implements GunProperty {
    private String divideflag;
    private String servicespath;
    private String redisaddr;

    public String getRedisaddr() {
        return redisaddr;
    }

    public String getServicespath() {
        return servicespath;
    }

    public String getDivideflag() {
        return divideflag;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
