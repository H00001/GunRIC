package top.gunplan.RIC.center;

import top.gunplan.netty.GunBootServer;

import top.gunplan.netty.filter.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.GunBootServerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CenterBoot {
    public static void main(String[] args) {
        GunBootServer server = GunBootServerFactory.getInstance();
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunDubboCenterStdFilter()).
                setHandle(new GunDubboCenterStdHandle());
        try {
            server.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
