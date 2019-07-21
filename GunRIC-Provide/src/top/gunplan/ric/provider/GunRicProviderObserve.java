package top.gunplan.ric.provider;

import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.netty.impl.GunNettyDefaultObserveImpl;
import top.gunplan.netty.impl.GunNettyPropertyManagerImpl;
import top.gunplan.netty.impl.propertys.GunNettyCoreProperty;
import top.gunplan.ric.common.GunRicExecutors;
import top.gunplan.ric.provider.constat.ConstatPool;
import top.gunplan.ric.provider.property.GunRicProvideProperty;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @author dosdrtt
 */
public class GunRicProviderObserve extends GunNettyDefaultObserveImpl {
    private ExecutorService threadPool = GunRicExecutors.newSignalExector();

    @Override
    public void onBooted(GunNettyCoreProperty property) {
        super.onBooted(property);
        threadPool.submit(() -> {
            try {
                GunRicPublishManager manage = new GunRicPublishManagerImpl(GunNettyPropertyManagerImpl.getProperty(GunRicProvideProperty.class));
                manage.publishInterface();
            } catch (IOException | ReflectiveOperationException e) {
                GunNettyContext.logger.setTAG(GunRicProviderObserve.class).error(e.getMessage(), "register fail", ConstatPool.Model.TAG);
            }
        });
    }

}
