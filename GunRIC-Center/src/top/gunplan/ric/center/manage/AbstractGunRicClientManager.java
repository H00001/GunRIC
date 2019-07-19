package top.gunplan.ric.center.manage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractGunRicClientManager<S extends GunRicClient> implements GunRicClientManager<S> {
    protected List<S> clients = new CopyOnWriteArrayList<>();

    @Override
    public List<S> clientList() {
        return clients;
    }


    @Override
    public abstract GunProviderAliveCheckResult aliveCheck();

    @Override
    public void register(S user) {
        clients.add(user);
    }

    @Override
    public S removeById(long id) {
        return null;
    }

    @Override
    public void remove(S user) {
        clients.remove(user);
    }
}
