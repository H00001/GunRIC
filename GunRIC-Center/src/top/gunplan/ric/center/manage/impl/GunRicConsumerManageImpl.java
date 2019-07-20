package top.gunplan.ric.center.manage.impl;

import top.gunplan.ric.center.manage.AbstractGunRicClientManager;
import top.gunplan.ric.center.manage.GunProviderAliveCheckResult;
import top.gunplan.ric.center.manage.GunRicConsumerClient;
import top.gunplan.ric.center.manage.GunRicConsumerManage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * GunRicConsumerManageImpl
 *
 * @author frank albert
 * @version 0.0.0.2
 * @date 2019-07-19 20:38
 */
public class GunRicConsumerManageImpl extends AbstractGunRicClientManager<GunRicConsumerClient> implements GunRicConsumerManage {


    @Override
    public List<GunRicConsumerClient> aireSavoirConsumers() {
        return clients;
    }


    @Override
    public GunProviderAliveCheckResult aliveCheck() {
        return null;
    }


}
