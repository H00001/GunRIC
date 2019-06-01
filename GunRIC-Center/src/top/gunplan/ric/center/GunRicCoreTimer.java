package top.gunplan.ric.center;

import top.gunplan.netty.GunTimer;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.nio.channels.SelectionKey;
import java.util.Set;


/**
 * GunRicCoreTimer
 *
 * @author dosdrtt
 * @date 2019/05/30
 */
public class GunRicCoreTimer implements GunTimer {
    @Override
    public int interval() {
        return 1;
    }

    @Override
    public int runingTimes() {
        return -1;
    }

    @Override
    public int doWork(Set<SelectionKey> set) {
        AbstractGunBaseLogUtil.debug("time running connection size " + set.size());
        return 0;
    }
}
