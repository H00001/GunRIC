package top.gunplan.ric.user;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author dosdrtt
 */
public class GunRicUserSocket extends Socket implements Delayed {
    public GunRicUserSocket(String addr, int port) throws IOException {
        super(addr, port);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(5000 - System.nanoTime(), TimeUnit.NANOSECONDS);

    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
