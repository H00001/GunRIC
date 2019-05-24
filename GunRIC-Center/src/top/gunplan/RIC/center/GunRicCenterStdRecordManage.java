package top.gunplan.RIC.center;


import java.io.*;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author dosdrtt
 * @concurrent GunRicCenterStdRecordManage
 */
public class GunRicCenterStdRecordManage implements GunRicCenterRecordManage {

    private List<GunRicCenterRecord> regexList = new CopyOnWriteArrayList<>();

    void register(GunRicCenterRecord registerRegex) {
        regexList.add(registerRegex);
    }

    void doRegex(final GunRicInterfaceBuffer.GunRicCdtInterface g, final InetSocketAddress address) {
        if (isFirst(g)) {
            regexList.parallelStream().forEach(reg -> reg.firstAdd(g, address));
        } else {
            regexList.parallelStream().forEach(reg -> reg.nextAdd(g, address));
        }
    }

    @Override
    public boolean isFirst(GunRicInterfaceBuffer.GunRicCdtInterface g) {
        return GunRicInterfaceBuffer.intermapping.get(g) == null;
    }

    private void close(Closeable... closeable) throws IOException {
        for (Closeable clos : closeable) {
            clos.close();
        }
    }


}
