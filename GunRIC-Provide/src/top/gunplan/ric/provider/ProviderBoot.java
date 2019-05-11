package top.gunplan.ric.provider;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.impl.propertys.GunProperty;
import top.gunplan.ric.provider.property.GunRICProvideProperty;
import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunNettyObserve;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.netty.filter.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dosdrtt
 * @date 1557359009
 */
public class ProviderBoot implements GunBootServerBase {
    public static void main(String[] args) {
        try {
            new ProviderBoot().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int sync() throws Exception {
        GunBootServer server = GunBootServerFactory.getInstance();
        server.registerObserve(new GunNettyObserve() {
            @Override
            public void onBooted(GunProperty gunProperty) {

            }

            @Override
            public boolean onBooting(GunProperty gunProperty) {
                try {
                    GunRicPublishManage manage = new GunRicPublishManage(GunNettyPropertyManagerImpl.getProperty("ric-provide"));
                    return manage.publishInterface();
                } catch (Exception e) {
                    AbstractGunBaseLogUtil.error(e);
                    AbstractGunBaseLogUtil.error("register fail", "[PROVIDER]");
                    return false;
                }
            }

            @Override
            public void onStop(GunProperty gunProperty) {

            }

            @Override
            public void onStatusChanged(GunNettyStatus gunNettyStatus) {

            }
        });
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        GunNettyPropertyManagerImpl.registerProperty("ric-provide", new GunRICProvideProperty());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunStdRicServerFilter()).
                setHandle(new GunRicProvideHandle());
        return server.sync();


    }
}
