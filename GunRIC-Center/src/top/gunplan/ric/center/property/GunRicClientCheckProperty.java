package top.gunplan.ric.center.property;

import top.gunplan.netty.GunProperty;
import top.gunplan.netty.anno.GunPropertyMap;

/**
 * GunRicClientCheckProperty
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-21 10:56
 */
@GunPropertyMap(name = "ric-center-client-check")
public class GunRicClientCheckProperty implements GunProperty {
    private int interval;

    public int getInterval() {
        return interval;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
