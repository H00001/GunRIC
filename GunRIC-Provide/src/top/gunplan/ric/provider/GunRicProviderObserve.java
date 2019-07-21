package top.gunplan.ric.provider;

import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.netty.impl.GunNettyDefaultObserveImpl;
import top.gunplan.netty.impl.GunNettyPropertyManagerImpl;
import top.gunplan.netty.impl.propertys.GunNettyCoreProperty;
import top.gunplan.ric.provider.constat.ConstatPool;
import top.gunplan.ric.provider.property.GunRicProvideProperty;

import java.io.IOException;

/**
 * @author dosdrtt
 */
public class GunRicProviderObserve extends GunNettyDefaultObserveImpl {
    //todo
    @Override
    public boolean onBooting(GunNettyCoreProperty gunProperty) {
        new Thread(() -> {
            try {
                GunRicPublishManagerImpl manage = new GunRicPublishManagerImpl(GunNettyPropertyManagerImpl.getProperty(GunRicProvideProperty.class));
                manage.publishInterface();
            } catch (IOException | ReflectiveOperationException e) {
                GunNettyContext.logger.setTAG(GunRicProviderObserve.class).error(e.getMessage(), "register fail", ConstatPool.Model.TAG);

            }
        }).start();
        return true;
    }
}
