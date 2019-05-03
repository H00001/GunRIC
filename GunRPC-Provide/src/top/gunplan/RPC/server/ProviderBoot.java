package top.gunplan.RPC.server;

import top.gunplan.RPC.server.property.GunRICProperty;
import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunNettyObserve;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.netty.filter.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.propertys.GunProPerty;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProviderBoot {
    public static void main(String[] args) {


        GunBootServer server = GunBootServerFactory.getInstance();
        server.registerObserve(new GunNettyObserve() {
            @Override
            public void onBoot(GunProPerty gunProPerty) {
            //    GunRPCPublishManage.publishInterface();

            }

            @Override
            public void onStop(GunProPerty gunProPerty) {

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
        GunNettyPropertyManagerImpl.registerProperty("ric-provide", new GunRICProperty());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunStdRPCServerFilter()).
                setHandle(new GunStdRPCHandle());
        try {
            server.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
