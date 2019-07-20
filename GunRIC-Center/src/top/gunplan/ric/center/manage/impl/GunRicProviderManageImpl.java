package top.gunplan.ric.center.manage.impl;

import top.gunplan.ric.center.manage.AbstractGunRicClientManager;
import top.gunplan.ric.center.manage.GunProviderAliveCheckResult;
import top.gunplan.ric.center.manage.GunRicProviderClient;
import top.gunplan.ric.center.manage.GunRicProviderManage;
import top.gunplan.ric.center.manage.impl.GunRicProviderClientImpl;
import top.gunplan.ric.protocol.GunAddressItem4;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static top.gunplan.ric.center.manage.GunRICStateRecorder.ConnectionState.LOSTCONECTION;

/**
 * GunRicProviderManageImpl
 *
 * @author frank albert
 * @version 0.0.0.2
 * @date 2019-07-19 20:50
 */
public class GunRicProviderManageImpl extends AbstractGunRicClientManager<GunRicProviderClient> implements GunRicProviderManage {


    @Override
    public List<GunRicProviderClient> removeUnuseProvider() {
        return null;
    }


    @Override
    public GunProviderAliveCheckResult aliveCheck() {
        AbstractGunBaseLogUtil.info("alive count is:" + clients.size());
        clients.parallelStream().forEach(GunRicProviderClient::doCheck);
        clients = clients.parallelStream().filter(who -> who.state() != LOSTCONECTION).collect(Collectors.toList());
        return null;
    }


}
