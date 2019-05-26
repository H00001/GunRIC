package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.GunRicInterfaceBuffer;
import top.gunplan.ric.center.anno.GunRicRegisterOrder;
import top.gunplan.ric.center.common.GunRicCenterStaticPath;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;

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
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunRicRespAddressProtocol.AddressItem address) {
        File f = new File(GunRicCenterStaticPath.SERVICES_PATH + "/" + g.getInterFaceName().replace(DT, L));
        f.mkdirs();
    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunRicRespAddressProtocol.AddressItem address) {

    }


}
