package top.gunplan.ric.center.manage.impl;

import top.gunplan.ric.center.manage.AbstractGunRicClientManager;
import top.gunplan.ric.center.manage.GunProviderAliveCheckResult;
import top.gunplan.ric.center.manage.GunRicProviderClient;
import top.gunplan.ric.center.manage.GunRicProviderManage;
import top.gunplan.ric.center.manage.impl.GunRicProviderClientImpl;
import top.gunplan.ric.protocol.GunAddressItem4;

import java.net.InetSocketAddress;
import java.util.List;

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

        clients.parallelStream().forEach(GunRicProviderClient::doCheck);

        return null;
    }

}
