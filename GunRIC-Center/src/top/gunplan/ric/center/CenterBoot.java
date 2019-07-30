package top.gunplan.ric.center;

import top.gunplan.netty.GunBootServer;
import top.gunplan.netty.GunBootServerBase;
import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.netty.impl.GunBootServerFactory;
import top.gunplan.netty.impl.GunNettyStdFirstFilter;
import top.gunplan.ric.center.cluster.GunRICClusterCheck;
import top.gunplan.ric.center.context.GunRicCenterInformationImpl;
import top.gunplan.ric.center.manage.check.GunRicCoreHeartTimer;
import top.gunplan.ric.center.property.GunRicCenterServiceUtilProperty;
import top.gunplan.ric.center.property.GunRicCenterServicesProperty;
import top.gunplan.ric.center.property.GunRicClientCheckProperty;
import top.gunplan.ric.common.GunRicExecutors;
import top.gunplan.ric.common.GunRicStdFilter;
import top.gunplan.ric.common.GunRicStdPolymerisationFilter;

/**
 * CenterBoot
 *
 * @author frank albert
 * @date 2019-06-08 08:42
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
        server = GunBootServerFactory.getInstance();
        server.registerObserve(new GunRicCenterObserve());

        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicCenterServicesProperty());
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicCenterServiceUtilProperty());
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicCenterInformationImpl());
        GunNettySystemServices.PROPERTY_MANAGER.registerProperty(new GunRicClientCheckProperty());
        server.setExecutors(GunRicExecutors.newValueBufferExector(100, 100), GunRicExecutors.newValueBufferExector(100, 100)).pipeline().addFilter(new GunNettyStdFirstFilter()).
                addFilter(new GunRicStdFilter()).
                addFilter(new GunRicStdPolymerisationFilter()).
                //addTimer(new GunRicCoreTimer()).
                        addTimer(new GunRicCoreHeartTimer()).
                addTimer(new GunRICClusterCheck()).
                setHandle(new GunRICCenterHandle());

        return server.sync();

    }

    @Override
    public int stop() throws InterruptedException {
        return server.stop();
    }

    @Override
    public boolean isSync() {
        return false;
    }

    @Override
    public void setSyncType(boolean b) {

    }
}
