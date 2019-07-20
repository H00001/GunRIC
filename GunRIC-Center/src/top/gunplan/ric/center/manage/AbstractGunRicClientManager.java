package top.gunplan.ric.center.manage;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * AbstractGunRicClientManager
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-20 08:25
 */
public abstract class AbstractGunRicClientManager<S extends GunRicClient> implements GunRicClientManager<S> {
    protected List<S> clients = new CopyOnWriteArrayList<>();

    @Override
    public void register(S user) {
        if (user!=null&&user.init()!=-3) {
            this.clients.add(user);
        }
    }

    @Override
    public List<S> clientList() {
        return Collections.unmodifiableList(clients);
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
