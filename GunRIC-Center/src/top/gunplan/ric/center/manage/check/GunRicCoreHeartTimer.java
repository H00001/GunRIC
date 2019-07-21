package top.gunplan.ric.center.manage.check;

import top.gunplan.netty.GunNettyTimer;
import top.gunplan.netty.impl.GunNettyPropertyManagerImpl;
import top.gunplan.ric.center.manage.GunRicConsumerManage;
import top.gunplan.ric.center.manage.impl.GunRicConsumerManageImpl;
import top.gunplan.ric.center.manage.GunRicProviderManage;
import top.gunplan.ric.center.manage.impl.GunRicProviderManageImpl;
import top.gunplan.ric.center.property.GunRicClientCheckProperty;

import java.nio.channels.SelectionKey;
import java.util.Set;

/**
 * @author dosdrtt
 */
public class GunRicCoreHeartTimer implements GunNettyTimer {
    private static volatile GunRicConsumerManage consumerManage = new GunRicConsumerManageImpl();
    public static volatile GunRicProviderManage providerManage = new GunRicProviderManageImpl();

    @Override
    public int interval() {
        return GunNettyPropertyManagerImpl.getProperty(GunRicClientCheckProperty.class).getInterval();
    }

    @Override
    public int runingTimes() {
        return 0;
    }

    @Override
    public int doWork(Set<SelectionKey> set) {
        providerManage.aliveCheck();
        return 0;
    }
}
