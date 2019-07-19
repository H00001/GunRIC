package top.gunplan.ric.center.check.impl;

import top.gunplan.netty.GunNettyTimer;
import top.gunplan.ric.center.check.GunRicCenterProviderCheck;
import top.gunplan.ric.center.manage.GunRicConsumerManage;
import top.gunplan.ric.center.manage.GunRicConsumerManageImpl;
import top.gunplan.ric.center.manage.GunRicProviderManage;
import top.gunplan.ric.center.manage.GunRicProviderManageImpl;

import java.nio.channels.SelectionKey;
import java.util.Set;

/**
 * @author dosdrtt
 */
public class GunRicCoreHeartTimer implements GunNettyTimer {
    private volatile GunRicConsumerManage consumerManage = new GunRicConsumerManageImpl();
    private volatile GunRicProviderManage providerManage = new GunRicProviderManageImpl();

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
        System.out.println("tick");
        providerManage.aliveCheck();
        return 0;
    }
}
