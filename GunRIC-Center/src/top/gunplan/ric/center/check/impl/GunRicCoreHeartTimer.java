package top.gunplan.ric.center.check.impl;

import top.gunplan.netty.GunNettyTimer;
import top.gunplan.ric.center.check.GunRicCenterProviderCheck;

import java.nio.channels.SelectionKey;
import java.util.Set;

/**
 * @author dosdrtt
 */
public class GunRicCoreHeartTimer implements GunNettyTimer {
    @Override
    public int interval() {
        return 18;
    }

    @Override
    public int runingTimes() {
        return 0;
    }

    @Override
    public int doWork(Set<SelectionKey> set) {
        new GunRicCenterProviderCheckImpl().doCheck();
        return 0;
    }
}
