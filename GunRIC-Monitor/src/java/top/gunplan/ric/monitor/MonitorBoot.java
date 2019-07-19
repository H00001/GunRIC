package top.gunplan.ric.monitor;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.utils.AbstractGunBaseLogUtil;

/**
 * @author dosdrtt
 * @see top.gunplan.netty.GunBootServerBase
 */
public class MonitorBoot implements GunBootServerBase {
    public static void main(String[] args) {
        try {
            new MonitorBoot().sync();
        } catch (Exception e) {
            AbstractGunBaseLogUtil.error(e);
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
