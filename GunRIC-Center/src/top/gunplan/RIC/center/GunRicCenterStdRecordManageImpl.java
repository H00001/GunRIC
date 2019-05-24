package top.gunplan.RIC.center;

import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.ric.protocol.RicProtocolParamType;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;


/**
 * GunRicCenterStdRecordManageImpl
 *
 * @author dosdrtt
 */
public class GunRicCenterStdRecordManageImpl implements GunRicCenterRecordManage {
    @Override
    public void addRecord(File file, GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) throws IOException {

        BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(file, true));
        if (GunRicInterfaceBuffer.intermapping.get(g) != null) {
            //      GunRicInterfaceBuffer.intermapping.get(new GunRicInterfaceBuffer.GunRicCdtInterface(hh, t, in, mn)).add(is);
            //
            writeBufferAddress(g, address, false);
        } else {
            writeFileFirst(g, bf);
            writeBufferAddress(g, address, true);
        }
        writeFileAddress(bf, address);
        close(bf);

    }

    private void writeFileFirst(GunRicInterfaceBuffer.GunRicCdtInterface g, BufferedOutputStream bf) throws IOException {
        bf.write(g.getParams().length);
        for (Class<?> aClass : g.getParams()) {
            RicProtocolParamType tp = RicProtocolParamType.valuefrom(aClass);
            bf.write(tp.val);
        }
        bf.write('\n');
    }

    private void close(Closeable... closeable) throws IOException {
        for (Closeable clos : closeable) {
            clos.close();
        }
    }

    private void writeFileAddress(BufferedOutputStream bf, final InetSocketAddress address) throws IOException {
        GunRicCenterServiceUtilProperty property = GunNettyPropertyManagerImpl.getProperty("ric-center-services-util");
        assert property != null;
        bf.write((address.getHostString() + property.getDivideflag() + address.getPort()).getBytes());
        bf.write('\n');
    }

    private void writeBufferAddress(GunRicInterfaceBuffer.GunRicCdtInterface g, final InetSocketAddress address, boolean firstWrite) {
        if (firstWrite) {
            List<InetSocketAddress> adds = new ArrayList<>();
            adds.add(address);
            GunRicInterfaceBuffer.intermapping.put(g, adds);
        } else {
            GunRicInterfaceBuffer.intermapping.get(g).add(address);
        }
    }
}
