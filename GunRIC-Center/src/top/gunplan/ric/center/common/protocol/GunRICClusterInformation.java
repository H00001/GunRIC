package top.gunplan.ric.center.common.protocol;


import top.gunplan.ric.center.context.GunRicCenterInformation;
import top.gunplan.ric.stand.GunRicCenterInlineStand;

import java.util.Set;

/**
 * AbstractGunRicCenterProtocol
 *
 * @author frank albert
 * @version 0.0.0.2
 * #date 2019-06-14 17:57
 */
public interface GunRICClusterInformation extends GunRicCenterInlineStand {

    GunRicCenterInformation master();

    Set<GunRicCenterInformation> slave();


}
