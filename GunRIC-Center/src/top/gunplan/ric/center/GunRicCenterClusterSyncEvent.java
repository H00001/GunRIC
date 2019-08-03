package top.gunplan.ric.center;

import top.gunplan.ric.center.common.protocol.GunRICClusterInformation;
import top.gunplan.ric.center.common.protocol.GunRICClusterSynchroizedInformation;
import top.gunplan.ric.center.context.GunCenterInformationManager;
import top.gunplan.ric.protocol.GunRicCommonRealDeal;

/**
 * GunRicCenterClusterSyncEvent
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-26 00:18
 */
public class GunRicCenterClusterSyncEvent implements GunRicCommonRealDeal<GunRICClusterInformation, GunRICClusterSynchroizedInformation> {
    @Override
    public GunRICClusterSynchroizedInformation dealDataEvent(GunRICClusterInformation protocol) {
        GunCenterInformationManager.updateInformation(protocol);

        return null;
    }
}
