package top.gunplan.RIC.center;


import top.gunplan.RIC.center.anno.GunRicRegisterOrder;
import top.gunplan.RIC.center.record.GunRicCenterRecordFailException;

import java.io.*;

import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author dosdrtt
 * @concurrent GunRicCenterStdRecordManage
 */
public class GunRicCenterStdRecordManage implements GunRicCenterRecordManage {

    private List<GunRicCenterRecord> regexList = new CopyOnWriteArrayList<>();
    private List<GunRicCenterRecord> firstList = new LinkedList<>();

    @Override
    public void registerFirst(GunRicCenterRecord registerRegex) {
        GunRicRegisterOrder order = registerRegex.getClass().getAnnotation(GunRicRegisterOrder.class);
        if (order != null) {
            firstList.add(order.index(), registerRegex);
        } else {
            firstList.add(registerRegex);
        }
    }

    @Override
    public void register(GunRicCenterRecord registerRegex) {
        regexList.add(registerRegex);
    }

    @Override
    public void doRegex(final GunRicInterfaceBuffer.GunRicCdtInterface g, final InetSocketAddress address) {
        if (isFirst(g)) {
            try {
                firstList.forEach(reg -> reg.firstAdd(g, address));
            } catch (GunRicCenterRecordFailException e) {
                throw e;
            }
            regexList.parallelStream().forEach(reg -> reg.firstAdd(g, address));
        } else {
            try {
                firstList.forEach(reg -> reg.nextAdd(g, address));
            } catch (GunRicCenterRecordFailException e) {
                throw e;
            }
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
