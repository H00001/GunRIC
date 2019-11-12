package top.gunplan.ric.provider;


import top.gunplan.netty.GunNettySystemService;
import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.netty.impl.property.GunNettyCoreProperty;
import top.gunplan.netty.observe.GunNettyDefaultObserve;
import top.gunplan.ric.common.GunRicExecutors;
import top.gunplan.ric.provider.constat.ConstatPool;
import top.gunplan.ric.provider.property.GunRicProvideProperty;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dosdrtt
 */
public class GunRicProviderObserve extends GunNettyDefaultObserve {
    private ExecutorService threadPool = GunRicExecutors.newSignalExector();

    @Override
    public void onBooted(GunNettyCoreProperty property) {
        super.onBooted(property);
        threadPool.submit(() -> {
            boolean next = true;
            for (; next; ) {
                try {
                    GunRicPublishManager manage = new GunRicPublishManagerImpl(GunNettySystemService.PROPERTY_MANAGER.acquireProperty(GunRicProvideProperty.class));
                    manage.publishInterface();
                    next = false;
                } catch (IOException | ReflectiveOperationException e) {
                    GunNettyContext.logger.setTAG(GunRicProviderObserve.class).error(e.getMessage(), ConstatPool.Model.TAG, "register fail");
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ignore) {

                }

            }


        });
    }

}
