package top.gunplan.ric.user;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dosdrtt
 * @concurrent
 */
public class GunRicUserSocketConnectPool extends AbstractGunRicUserConnectPool {
    private Socket[] sockets;
    private AtomicInteger nowused = new AtomicInteger(0);

    public GunRicUserSocketConnectPool(String address, int port, int count) throws IOException {
        super(address, port, count);
        sockets = new Socket[count];
        for (int i = 0; i < count; i++) {
            sockets[i] = new Socket(address, port);
        }
    }


    public Socket getSocket() {
        Socket ss = sockets[nowused.get()];
        nowused.incrementAndGet();
        return ss;
    }

    @Override
    public int avaliableCount() {
        return 0;
    }

    @Override
    public int allCount() {
        return 0;
    }
}
