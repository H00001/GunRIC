package top.gunplan.ric.center.manage.check;

import top.gunplan.netty.GunNettyTimer;
import top.gunplan.netty.anno.GunHandleTag;
import top.gunplan.netty.anno.GunTimeExecutor;
import top.gunplan.ric.center.manage.GunRicConsumerManage;
import top.gunplan.ric.center.manage.GunRicProviderManage;
import top.gunplan.ric.center.manage.impl.GunRicConsumerManageImpl;
import top.gunplan.ric.center.manage.impl.GunRicProviderManageImpl;

/**
 * @author dosdrtt
 */
public final class GunRicCoreHeartTimer implements GunNettyTimer {
    private static volatile GunRicConsumerManage consumerManage = new GunRicConsumerManageImpl();
    public static volatile GunRicProviderManage providerManage = new GunRicProviderManageImpl();


    @GunTimeExecutor(interval = 1, t = @GunHandleTag(name = "it", id = 3112))
    public int doWork() {
        providerManage.aliveCheck();
        return 0;
    }

    @Override
    public boolean timeExecuteError(String s, ReflectiveOperationException e) {
        return false;
    }
}
