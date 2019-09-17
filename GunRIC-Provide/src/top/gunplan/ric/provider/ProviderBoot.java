package top.gunplan.ric.provider;

import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.GunNettyStdFirstFilter;
import top.gunplan.ric.common.GunRicStdFilter;
import top.gunplan.ric.provider.property.GunRicProvideProperty;

/**
 * @author dosdrtt
 * #date 1557359009
 */
public class ProviderBoot implements GunBootServerBase {
    private volatile GunBootServer server;

    public static void main(String[] args) {
        try {
            new ProviderBoot().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int sync() throws Exception {
        server = GunBootServerFactory.newInstance();
        server.registerObserve(new GunRicProviderObserve());
        GunNettySystemServices.PROPERTY_MANAGER.
                registerProperty(new GunRicProvideProperty());
        server.setExecutors(100, 100).onHasChannel(c ->
                c.addDataFilter(new GunNettyStdFirstFilter()).
                        addDataFilter(new GunRicStdFilter()).
                        setHandle(new GunRicProvideHandle())
        );
        return server.sync();
    }

    @Override
    public int stop() throws InterruptedException {
        return server.stop();
    }
}
