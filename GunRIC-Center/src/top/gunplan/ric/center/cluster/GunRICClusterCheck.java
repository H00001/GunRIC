package top.gunplan.ric.center.cluster;

import top.gunplan.netty.GunNettyTimer;
import top.gunplan.ric.center.context.F;

import java.nio.channels.SelectionKey;
import java.util.Set;

/**
 * GunRICClusterCheck
 *
 * @author frank albert
 * @version 0.0.0.2
 * @date 2019-07-25 23:53
 */
public class GunRICClusterCheck implements GunNettyTimer {

    public int doWork(Set<SelectionKey> set) {
        F.LOG.info("cluster check");
        return 0;
    }

    @Override
    public boolean timeExecuteError(String s, ReflectiveOperationException e) {
        return false;
    }
}
