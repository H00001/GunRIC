package top.gunplan.ric.center.context;

import top.gunplan.ric.center.common.protocol.GunRICClusterInformation;

import java.util.HashSet;
import java.util.Set;

/**
 * GunCenterInformationManager
 *
 * @author frank albert
 * @version 0.0.2.1
 * @date 2019-08-03 21:53
 */
public final class GunCenterInformationManager {

    private static final GunCenterInformationManager MANAGER_INSTANCE = new GunCenterInformationManager();

    static {

    }

    private boolean iAmMaster = true;
    private GunRicCenterInformation master;
    private Set<GunRicCenterInformation> slaves = new HashSet<>();

    private GunCenterInformationManager() {

    }

    public static synchronized void updateInformation(GunRICClusterInformation information) {
        MANAGER_INSTANCE.updateMaster(information.master());
        MANAGER_INSTANCE.updateSlaves(information.slave());
        MANAGER_INSTANCE.iAmMaster = false;
    }


    public static synchronized void updateMasterSelf(GunRicCenterInformation information) {
        MANAGER_INSTANCE.updateMaster(information);
        MANAGER_INSTANCE.iAmMaster = true;
    }

    public static GunCenterInformationManager instance() {
        return MANAGER_INSTANCE;
    }

    public boolean iAmMaster() {
        return iAmMaster;
    }

    public GunRicCenterInformation master() {
        return master;
    }

    private void updateMaster(GunRicCenterInformation master) {
        this.master = master;
    }

    public Set<GunRicCenterInformation> slaves() {
        return slaves;
    }

    private void updateSlaves(Set<GunRicCenterInformation> slaves) {
        this.slaves = slaves;
    }
}
