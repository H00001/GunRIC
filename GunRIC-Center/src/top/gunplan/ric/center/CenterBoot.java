package top.gunplan.ric.center;

import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.GunNettyStdFirstFilter;
import top.gunplan.netty.impl.property.GunGetPropertyFromBaseFile;
import top.gunplan.ric.center.context.GunRicCenterInformationImpl;
import top.gunplan.ric.center.manage.check.GunRicCoreHeartTimer;
import top.gunplan.ric.center.property.GunRicCenterServiceUtilProperty;
import top.gunplan.ric.center.property.GunRicCenterServicesProperty;
import top.gunplan.ric.center.property.GunRicClientCheckProperty;
import top.gunplan.ric.common.GunRicStdFilter;
import top.gunplan.ric.common.GunRicStdPolymerisationFilter;

/**
 * CenterBoot
 *
 * @author frank albert
 * #date 2019-06-08 08:42
 */

public class CenterBoot implements GunBootServerBase {
    private volatile GunBootServer server;


    public static void main(String[] args) {
        try {
            new CenterBoot().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int sync() throws Exception {
        GunNettySystemServices.PROPERTY_MANAGER.setStrategy(new GunGetPropertyFromBaseFile(System.getProperty("config")));
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicCenterServicesProperty());
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicCenterServiceUtilProperty());
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicCenterInformationImpl());
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicClientCheckProperty());

        server = GunBootServerFactory.newInstance();
        server.registerObserve(new GunRicCenterObserve());
        server.useStealMode(true).
                setExecutors(100, 100).
                onHasChannel(ch -> ch.
                        addDataFilter(new GunNettyStdFirstFilter()).
                        addDataFilter(new GunRicStdFilter()).
                        addDataFilter(new GunRicStdPolymerisationFilter()).
                        addNettyTimer(new GunRicCoreHeartTimer()).
                        setHandle(new GunRICCenterHandle())
                );
        return server.sync();
    }

    @Override
    public int stop() throws InterruptedException {
        return server.stop();
    }
}
