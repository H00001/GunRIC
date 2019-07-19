package top.gunplan.ric.center.manage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * GunRicConsumerManageImpl
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 20:38
 */
public class GunRicConsumerManageImpl extends AbstractGunRicClientManager<GunRicConsumerClient> implements GunRicConsumerManage {

    private List<GunRicConsumerClient> clients = new CopyOnWriteArrayList<>();

    @Override
    public List<GunRicConsumerClient> aireSavoirConsumers() {
        return clients;
    }

    @Override
    public List<GunRicConsumerClient> clientList() {
        return clients;
    }

    @Override
    public GunProviderAliveCheckResult aliveCheck() {
        return null;
    }


}
