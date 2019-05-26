package top.gunplan.ric.provider;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.impl.propertys.GunProperty;
import top.gunplan.ric.common.GunRicStdFilter;
import top.gunplan.ric.provider.property.GunRicProvideProperty;
import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunNettyObserve;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.netty.filter.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.utils.AbstractGunBaseLogUtil;

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
        server.registerObserve(new GunRicProviderObserve());
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        GunNettyPropertyManagerImpl.registerProperty(new GunRicProvideProperty());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunRicStdFilter()).
                setHandle(new GunRicProvideHandle());
        return server.sync();


    }
}
