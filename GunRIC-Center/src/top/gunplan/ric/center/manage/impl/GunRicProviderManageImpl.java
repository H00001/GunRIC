package top.gunplan.ric.center.manage.impl;

import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.ric.center.manage.AbstractGunRicClientManager;
import top.gunplan.ric.center.manage.GunProviderAliveCheckResult;
import top.gunplan.ric.center.manage.GunRicProviderClient;
import top.gunplan.ric.center.manage.GunRicProviderManage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static top.gunplan.ric.center.manage.GunRICStateRecorder.ConnectionState.LOSTCONECTION;

/**
 * GunRicProviderManageImpl
 *
 * @author frank albert
 * @version 0.0.0.3
 * @date 2019-07-19 20:50
 */
public class GunRicProviderManageImpl extends AbstractGunRicClientManager<GunRicProviderClient> implements GunRicProviderManage {


    @Override
    public List<GunRicProviderClient> removeUnuseProvider() {
        return null;
    }


    @Override
    public GunProviderAliveCheckResult aliveCheck() {
        GunNettyContext.logger.info("alive count is:" + clients.size());
        clients.parallelStream().forEach(GunRicProviderClient::doCheck);
        clients = clients.parallelStream().filter(who -> who.state() != LOSTCONECTION).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        return null;
    }


}
