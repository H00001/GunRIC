package top.gunplan.ric.common;


import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author dosdrtt
 */
public class GunRicUserConnectionFactory {

    private final static DelayQueue<AbstractGunRicCommonProtocolSocket> SOCKETS = new DelayQueue<>();

    private static volatile boolean isScan = false;

    private static ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);

    static {
        scan();
    }

    public static AbstractGunRicCommonProtocolSocket newSocket(String ip, int port) throws IOException {
        AbstractGunRicCommonProtocolSocket ss = new GunRicCommonSocketImpl(ip, port);
        SOCKETS.offer(ss);
        return ss;
    }

    public static AbstractGunRicCommonSocket newSocket(String addr) throws IOException {
        return newSocket(addr.split("-")[0], Integer.parseInt(addr.split("-")[1]));
    }

    public static AbstractGunRicCommonProtocolSocket newSocket(InetSocketAddress addr) throws IOException {
        return newSocket(addr.getHostString(), addr.getPort());
    }

    static AbstractGunRicCommonProtocolSocket[] newSocket(InetSocketAddress[] addr) throws IOException {
        AbstractGunRicCommonProtocolSocket[] sss = new GunRicCommonSocketImpl[addr.length];
        for (int i = 0; i < addr.length; i++) {
            sss[i] = newSocket(addr[i]);
        }
        return sss;
    }


    private static void stopScan() {
        isScan = false;
        service.shutdown();
    }


    static void scan() {
        service.scheduleAtFixedRate(() -> {
            try {

                AbstractGunRicCommonProtocolSocket take = SOCKETS.take();
                if (!take.isUsed() || take.isClosed()) {
                    AbstractGunBaseLogUtil.debug("collection {0}".replace("{0}", take.getRemoteSocketAddress().toString()));
                    take.close();
                }
            } catch (Exception e) {
                AbstractGunBaseLogUtil.error(e);
            }
        }, 5000, 1000, TimeUnit.MILLISECONDS);

    }


}
