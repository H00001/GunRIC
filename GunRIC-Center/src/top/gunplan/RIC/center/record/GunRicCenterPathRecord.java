package top.gunplan.RIC.center.record;

import top.gunplan.RIC.center.GunRicCenterRecord;
import top.gunplan.RIC.center.GunRicInterfaceBuffer;
import top.gunplan.RIC.center.anno.GunRicRegisterOrder;
import top.gunplan.RIC.center.common.GunRicCenterStaticPath;
import top.gunplan.ric.protocol.util.PathUtil;

import java.io.File;
import java.net.InetSocketAddress;

/**
 * @author dosdrtt
 */
@GunRicRegisterOrder(index = 0)
public class GunRicCenterPathRecord implements GunRicCenterRecord {
    private final static String L = "/";
    private final static String DT = ".";

    @Override
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) {
        File f = new File(GunRicCenterStaticPath.SERVICES_PATH + "/" + g.getInterFaceName().replace(DT, L));
        f.mkdirs();
    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) {

    }
}
