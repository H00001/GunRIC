package top.gunplan.ric.provider;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.netty.impl.GunNettyStdFirstFilter;

import top.gunplan.ric.common.GunRicStdFilter;
import top.gunplan.ric.common.GunRicThreadFactory;
import top.gunplan.ric.provider.property.GunRicProvideProperty;
import top.gunplan.netty.GunBootServer;

import top.gunplan.netty.impl.GunBootServerFactory;


import java.util.concurrent.*;

/**
 * @author dosdrtt
 * @date 1557359009
 */
public class ProviderBoot implements GunBootServerBase {
    private volatile GunBootServer server;

    public static void main(String[] args) {
        try {
            new ProviderBoot().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int sync() throws Exception {
        server = GunBootServerFactory.getInstance();
        server.registerObserve(new GunRicProviderObserve());
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new GunRicThreadFactory());
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new GunRicThreadFactory());
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicProvideProperty());
        server.setExecutors(es0, es1).pipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunRicStdFilter()).
                setHandle(new GunRicProvideHandle());
        return server.sync();


    }

    @Override
    public int stop() throws InterruptedException {
        return server.stop();
    }
}
