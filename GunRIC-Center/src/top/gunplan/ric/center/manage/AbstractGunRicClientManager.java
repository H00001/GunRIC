package top.gunplan.ric.center.manage;

import top.gunplan.ric.center.GunRicCenterStdRecordManage;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Stream;

/**
 * AbstractGunRicClientManager
 *
 * @author frank albert
 * @version 0.0.0.4
 * #date 2019-07-20 08:25
 */
public abstract class AbstractGunRicClientManager<S extends GunRicClient> implements GunRicClientManager<S> {
    protected Set<S> clients = new ConcurrentSkipListSet<>();

    @Override
    public void informToRecorder(Stream<S> stream) {
        stream.forEach(which -> GunRicCenterStdRecordManage.Instance.getHinstance().eraser(which.addressInformation()));
    }

    @Override
    public Set<S> clientList() {
        return clients;
    }

    @Override
    public int normalSize() {
        return clients.size();
    }

    @Override
    public abstract GunProviderAliveCheckResult aliveCheck();


    @Override
    public S removeById(long id) {
        return null;
    }


    @Override
    public void remove(S user) {
        clients.remove(user);
    }
}
