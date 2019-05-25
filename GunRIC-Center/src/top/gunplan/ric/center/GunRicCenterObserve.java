package top.gunplan.ric.center;

import top.gunplan.netty.impl.GunNettyDefaultObserveImpl;
import top.gunplan.netty.impl.propertys.GunProperty;
import top.gunplan.utils.AbstractGunBaseLogUtil;

/**
 * GunRicCenterObserve
 *
 * @author dosdrtt
 */
public class GunRicCenterObserve extends GunNettyDefaultObserveImpl {
    @Override
    public boolean onBooting(GunProperty gunProperty) {
        super.onBooting(gunProperty);
        try {
            return GunRicRegisterManage.loadRegister();
        } catch (Exception e) {
            AbstractGunBaseLogUtil.error(e);
            return false;
        }
    }
}
