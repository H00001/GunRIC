package top.gunplan.RIC.center;

import top.gunplan.netty.GunBootServer;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.filter.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.GunBootServerFactory;

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
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunRicDividePacketFilter()).
                addFilter(new GunDubboCenterStdFilter()).
                setHandle(new GunDubboCenterNewHandle());

        return server.sync();

    }
}
