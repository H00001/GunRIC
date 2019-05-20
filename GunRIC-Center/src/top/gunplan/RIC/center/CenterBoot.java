package top.gunplan.RIC.center;

import top.gunplan.netty.GunBootServer;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.GunNettyObserve;
import top.gunplan.netty.filter.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.propertys.GunProperty;

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
        server.registerObserve(new GunNettyObserve() {
            @Override
            public void onBooted(GunProperty gunProperty) {

            }

            @Override
            public boolean onBooting(GunProperty gunProperty) {
                //       return GunRicRegisterManage.loadRegister();
                return true;
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
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunRicDividePacketFilter()).
                addFilter(new GunRicCenterStdFilter()).
                setHandle(new GunRicCenterNewHandle());

        return server.sync();

    }
}
