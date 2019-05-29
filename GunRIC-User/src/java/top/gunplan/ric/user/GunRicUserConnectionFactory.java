package top.gunplan.ric.user;


import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.util.concurrent.*;

/**
 * @author dosdrtt
 */
class GunRicUserConnectionFactory {

    private final static DelayQueue<GunRicUserSocket> SOCKETS = new DelayQueue<>();

    private static volatile boolean isScan = true;

    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    static Socket newSocket(String ip, int port) throws IOException {
        GunRicUserSocket ss = new GunRicUserSocket(ip, port);
        SOCKETS.offer(ss);
        return ss;
    }

    private static void stopScan() {
        isScan = false;
        service.shutdown();
    }


    static void scan() {
        service.scheduleAtFixedRate(() -> {
            try {
                GunRicUserSocket take = SOCKETS.take();
                take.close();
                AbstractGunBaseLogUtil.error("close");
            } catch (Exception e) {
                AbstractGunBaseLogUtil.error(e);
            }
        }, 5000, 10000, TimeUnit.MILLISECONDS);

    }

    static Socket newSocket(InetSocketAddress address) throws IOException {
        return newSocket(address.getHostString(), address.getPort());
    }
}
