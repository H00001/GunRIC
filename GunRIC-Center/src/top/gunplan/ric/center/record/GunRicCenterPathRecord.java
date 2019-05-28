package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCdtInterface;
import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.center.anno.GunRicRegisterOrder;
import top.gunplan.ric.center.common.GunRicCenterStaticPath;
import top.gunplan.ric.protocol.GunAddressItem;

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
    public void firstAdd(GunRicCdtInterface g, GunAddressItem address) {
        File f = new File(GunRicCenterStaticPath.SERVICES_PATH + "/" + g.getInterFaceName().replace(DT, L));
        f.mkdirs();
    }

    @Override
    public void nextAdd(GunRicCdtInterface g, GunAddressItem address) {

    }


}
