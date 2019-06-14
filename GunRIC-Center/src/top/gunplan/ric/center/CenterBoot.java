package top.gunplan.ric.center;

import top.gunplan.netty.impl.GunNettyStdFirstFilter;
import top.gunplan.ric.center.check.impl.GunRicCenterProviderCheckImpl;
import top.gunplan.ric.center.check.impl.GunRicCoreHeartTimer;
import top.gunplan.ric.center.property.GunRicCenterServicesProperty;
import top.gunplan.ric.center.property.GunRicCenterServiceUtilProperty;
import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.impl.GunNettyPropertyManagerImpl;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.ric.common.GunRicStdFilter;
import top.gunplan.ric.common.GunRicThreadFactory;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CenterBoot
 *
 * @author frank albert
 * @date 2019-06-08 08:42
 */

public class CenterBoot implements GunBootServerBase {
    public static void main(String[] args) {
        try {
            new CenterBoot().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int sync() throws Exception {
        GunBootServer server = GunBootServerFactory.getInstance();
        server.registerObserve(new GunRicCenterObserve());
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20000), new GunRicThreadFactory(CenterBoot.class.getSimpleName()));
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20000), new GunRicThreadFactory(CenterBoot.class.getSimpleName()));

        GunNettyPropertyManagerImpl.registerProperty(new GunRicCenterServicesProperty());
        GunNettyPropertyManagerImpl.registerProperty(new GunRicCenterServiceUtilProperty());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunRicStdFilter()).
                //addTimer(new GunRicCoreTimer()).
                        addTimer(new GunRicCoreHeartTimer()).
                setHandle(new GunRicCenterHandle());

        return server.sync();

    }
}
