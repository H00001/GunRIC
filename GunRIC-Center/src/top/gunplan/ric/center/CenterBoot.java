package top.gunplan.ric.center;

import top.gunplan.ric.center.property.GunRicCenterServicesProperty;
import top.gunplan.ric.center.property.GunRicCenterServiceUtilProperty;
import top.gunplan.netty.GunBootServer;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.GunNettyObserve;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.netty.filter.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.propertys.GunProperty;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dosdrtt
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
                new LinkedBlockingQueue<>());
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        GunNettyPropertyManagerImpl.registerProperty(new GunRicCenterServicesProperty());
        GunNettyPropertyManagerImpl.registerProperty(new GunRicCenterServiceUtilProperty());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunRicDividePacketFilter()).
                addFilter(new GunRicCenterStdFilter()).
                setHandle(new GunRicCenterNewHandle());

        return server.sync();

    }
}
