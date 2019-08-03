package top.gunplan.ric.center;


import top.gunplan.ric.center.anno.GunRicRegisterOrder;
import top.gunplan.ric.center.record.AbstractGunRicProxyRecord;
import top.gunplan.ric.center.record.GunRicCenterRecordFailException;
import top.gunplan.ric.common.F;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author dosdrtt
 * @date 2019.05.27
 * @concurrent GunRicCenterStdRecordManage
 */
public class GunRicCenterStdRecordManage implements GunRICCenterRecordManager {
    private List<AbstractGunRicProxyRecord> regexList = new CopyOnWriteArrayList<>();

    private GunRicCenterStdRecordManage() {
        //ForkJoinPool pool = new ForkJoinPool();


    }

    @Override
    public void eraser(final GunAddressItemInterface address) {
        F.LOG.debug(String.format("remove node %s:%d", address.getAddress(), address.getPort()));
        firstList.parallelStream().forEach(one -> one.remove(address));
    }

    @Override
    public void register(AbstractGunRicProxyRecord registerRegex) {
        regexList.add(registerRegex);
    }

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
    public void registerLoop(AbstractGunRicProxyRecord record) {
        while (record != null) {
            register(record);
            record = record.getLastRecord();
        }
    }

    @Override
    public AbstractGunRicProxyRecord getFirstRecord() {
        return regexList.get(0);
    }

    @Override
    public void doRegex(final BaseGunRicServerInformation g, final GunAddressItemInterface address) {
        if (isFirst(g)) {
            firstList.forEach(reg -> reg.firstAdd(g, address));
            regexList.parallelStream().forEach(reg -> reg.firstAdd(g, address));
        } else {
            try {
                firstList.forEach(reg -> reg.nextAdd(g, address));
            } catch (GunRicCenterRecordFailException e) {
                F.LOG.error(e);
            }
            regexList.parallelStream().forEach(reg -> reg.nextAdd(g, address));
        }
    }

    /**
     * signal instance mode
     */
    public static class Instance {
        private static GunRicCenterStdRecordManage hinstance = new GunRicCenterStdRecordManage();

        public static GunRicCenterStdRecordManage getHinstance() {
            return hinstance;
        }
    }

    @Override
    public boolean isFirst(BaseGunRicServerInformation g) {
        return GunRicInterfaceBuffer.newInstance().get(g) == null;
    }


}
