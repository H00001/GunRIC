package top.gunplan.ric.center.record;

import top.gunplan.ric.center.common.GunRicCenterStaticPath;
import top.gunplan.ric.center.property.GunRicCenterServiceUtilProperty;
import top.gunplan.ric.center.GunRicInterfaceBuffer;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.ric.protocol.GunAddressItem;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;
import top.gunplan.ric.protocol.RicProtocolParamType;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * GunRicCenterFileRecord
 * write and record
 *
 * @author dosdrtt
 */
public class GunRicCenterFileRecord extends AbstractGunRicProxyRecord {


    private GunRicCenterServiceUtilProperty property = GunNettyPropertyManagerImpl.getProperty(GunRicCenterServiceUtilProperty.class);

    public GunRicCenterFileRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
    }

    private File initFile(final GunRicInterfaceBuffer.GunRicCdtInterface g) {
        return new File(GunRicCenterStaticPath.SERVICES_PATH + "/" + g.getInterFaceName().replace(".", "/") + "/" + g.getMethodName() + property.getDivideflag() + g.getId());
    }

    @Override
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunAddressItem address) {
        try {
            File recordfile = initFile(g);
            if (!recordfile.exists()) {
                BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(recordfile, true));
                writeFileFirst(g, bf);
                writeFileAddress(bf, address);
                bf.close();
            }
        } catch (IOException e) {
            throw new GunRicCenterRecordFailException("file cannot open to record");
        }

    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunAddressItem address) {
        try {
            BufferedOutputStream bf;
            bf = new BufferedOutputStream(new FileOutputStream(initFile(g), true));
            writeFileAddress(bf, address);
            bf.close();
        } catch (IOException e) {
            throw new GunRicCenterRecordFailException("file cannot open to record");
        }
    }

    @Override
    List<GunAddressItem> getAddressBase(GunRicInterfaceBuffer.GunRicCdtInterface g) {
        return null;
    }


    private void writeFileAddress(BufferedOutputStream bf, final GunAddressItem address) throws IOException {
        bf.write((address.getAddress() + property.getDivideflag() + address.getPort()).getBytes());
        bf.write('\n');
    }

    private void writeFileFirst(GunRicInterfaceBuffer.GunRicCdtInterface g, BufferedOutputStream bf) throws IOException {
        bf.write(g.getParams().length);
        for (Class<?> aClass : g.getParams()) {
            RicProtocolParamType tp = RicProtocolParamType.valuefrom(aClass);
            bf.write(tp.val);
        }
        bf.write('\n');
    }
}
