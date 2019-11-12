package top.gunplan.ric.center.record;


import top.gunplan.netty.GunNettySystemService;
import top.gunplan.ric.center.common.GunRicCenterStaticPath;
import top.gunplan.ric.center.property.GunRicCenterServiceUtilProperty;
import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;
import top.gunplan.ric.protocol.RicProtocolParamType;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

/**
 * GunRicCenterFileRecord
 * write and record
 *
 * @author dosdrtt
 */
public class GunRicCenterFileRecord extends AbstractGunRicProxyRecord {


    private GunRicCenterServiceUtilProperty property = GunNettySystemService.PROPERTY_MANAGER.acquireProperty(GunRicCenterServiceUtilProperty.class);

    public GunRicCenterFileRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
    }

    private File initFile(final BaseGunRicServerInformation g) {
        return new File(GunRicCenterStaticPath.SERVICES_PATH + "/" + g.getInterfaceName().replace(".", "/") + "/" + g.getMethodName() + property.getDivideFlag() + g.getId());
    }

    @Override
    public void firstAdd(BaseGunRicServerInformation g, GunAddressItemInterface address) {
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
    public void nextAdd(BaseGunRicServerInformation g, GunAddressItemInterface address) {
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
    public void remove(GunAddressItemInterface address) {

    }

    @Override
    Set<GunAddressItemInterface> getAddressBase(BaseGunRicServerInformation g) {
        return null;
    }


    private void writeFileAddress(BufferedOutputStream bf, final GunAddressItemInterface address) throws IOException {
        bf.write((address.getAddress() + property.getDivideFlag() + address.getPort()).getBytes());
        bf.write('\n');
    }

    private void writeFileFirst(BaseGunRicServerInformation g, BufferedOutputStream bf) throws IOException {
        bf.write(g.getParams().length);
        for (Class<?> aClass : g.getParams()) {
            RicProtocolParamType tp = RicProtocolParamType.valuefrom(aClass);
            bf.write(tp.val);
        }
        bf.write('\n');
    }
}
