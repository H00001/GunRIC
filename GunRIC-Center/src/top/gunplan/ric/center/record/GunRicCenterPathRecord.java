package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.anno.GunRicRegisterOrder;
import top.gunplan.ric.center.common.GunRicCenterStaticPath;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.io.File;

/**
 * @author dosdrtt
 */
@GunRicRegisterOrder(index = 0)
public class GunRicCenterPathRecord implements GunRicCenterRecord {
    private final static String L = "/";
    private final static String DT = ".";

    public GunRicCenterPathRecord(GunRicCenterRecord lastRecord) {

    }

    @Override
    public void firstAdd(BaseGunRicCdt g, GunAddressItemInterface address) {
        File f = new File(GunRicCenterStaticPath.SERVICES_PATH + "/" + g.getInterfaceName().replace(DT, L));
        f.mkdirs();
    }

    @Override
    public void nextAdd(BaseGunRicCdt g, GunAddressItemInterface address) {

    }


}
