package top.gunplan.ric.monitor;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.common.GunNettyContext;

/**
 * @author dosdrtt
 * @see top.gunplan.netty.GunBootServerBase
 */
public class MonitorBoot implements GunBootServerBase {
    public static void main(String[] args) {
        try {
            new MonitorBoot().sync();
        } catch (Exception e) {
            GunNettyContext.logger.error(e);
        }
    }

    @Override
    public int sync() throws Exception {
        return 0;
    }

    @Override
    public int stop() throws InterruptedException {
        return 0;
    }
}
