package top.gunplan.ric.provider;

import top.gunplan.netty.impl.GunNettyPropertyManagerImpl;
import top.gunplan.netty.impl.GunNettyDefaultObserveImpl;
import top.gunplan.netty.impl.propertys.GunNettyCoreProperty;
import top.gunplan.ric.provider.property.GunRicProvideProperty;
import top.gunplan.utils.AbstractGunBaseLogUtil;

/**
 * @author dosdrtt
 */
public class GunRicProviderObserve extends GunNettyDefaultObserveImpl {
    @Override
    public boolean onBooting(GunNettyCoreProperty gunProperty) {
        try {
            GunRicPublishManage manage = new GunRicPublishManage(GunNettyPropertyManagerImpl.getProperty(GunRicProvideProperty.class));
            return manage.publishInterface();
        } catch (Exception e) {
            AbstractGunBaseLogUtil.error(e.getMessage(), "register fail", "[PROVIDER]");
            return false;
        }
    }
}
