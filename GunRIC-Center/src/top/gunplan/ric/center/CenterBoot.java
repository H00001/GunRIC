package top.gunplan.ric.center;

import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.GunNettySystemService;
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
        GunNettySystemService.PROPERTY_MANAGER.setStrategy(new GunGetPropertyFromBaseFile(System.getProperty("config")));
        GunNettySystemService.PROPERTY_MANAGER.registerProperty(new GunRicCenterServicesProperty());
        GunNettySystemService.PROPERTY_MANAGER.registerProperty(new GunRicCenterServiceUtilProperty());
        GunNettySystemService.PROPERTY_MANAGER.registerProperty(new GunRicCenterInformationImpl());
        GunNettySystemService.PROPERTY_MANAGER.registerProperty(new GunRicClientCheckProperty());

        server = GunBootServerFactory.newInstance();
        server.registerObserve(new GunRicCenterObserve());
        server.useStealMode(true).
                setExecutors(100, 100).
                onHasChannel(ch -> ch.
                        addDataFilter(new GunNettyStdFirstFilter()).
                        addDataFilter(new GunRicStdFilter()).
                        addDataFilter(new GunRicStdPolymerisationFilter()).
                        addNettyTimer(new GunRicCoreHeartTimer()).
                        setHandle(new GunRicCenterHandle())
                );
        return server.sync();
    }

    @Override
    public int stop() throws InterruptedException {
        return server.stop();
    }
}
