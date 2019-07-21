package top.gunplan.ric.common;

import java.io.IOException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * GunRicCommonSocketImpl
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-14 11:43
 */
public class GunRicCommonSocketImpl extends AbstractGunRicCommonProtocolSocket {
    GunRicCommonSocketImpl(String addr, int port) throws IOException {
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
