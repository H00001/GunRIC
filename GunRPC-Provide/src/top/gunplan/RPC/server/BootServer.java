package top.gunplan.RPC.server;

import top.gunplan.RPC.server.filters.GunStdRPCServerFilter;
import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.filters.GunNettyStdFirstFilter;
import top.gunplan.netty.filters.GunStdHttp2Filter;
import top.gunplan.netty.handles.GunStdHttpHandle;
import top.gunplan.netty.impl.GunBootServerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BootServer {
    public static void main(String[] args)
    {
        GunBootServer server = GunBootServerFactory.getInstance();
        ExecutorService es0 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        ExecutorService es1 = new ThreadPoolExecutor(100, 1000,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        server.setExecuters(es0, es1).getPipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunStdRPCServerFilter()).
                //  addFilter(new GunHttpdHostCheck()).
                        setHandle(new GunStdRPCHandle());
        try {
            server.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
