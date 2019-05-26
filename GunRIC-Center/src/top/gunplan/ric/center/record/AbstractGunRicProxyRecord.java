package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.GunRicInterfaceBuffer;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author dosdrtt
 */
public abstract class AbstractGunRicProxyRecord implements GunRicCenterRecord {

    private AbstractGunRicProxyRecord lastRecord;

    AbstractGunRicProxyRecord(AbstractGunRicProxyRecord lastRecord) {
        this.lastRecord = lastRecord;
    }

    public AbstractGunRicProxyRecord getLastRecord() {
        return lastRecord;
    }

    /**
     * firstAdd
     *
     * @param g       ric record from information
     * @param address address info
     */
    @Override
    public abstract void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address);


    /**
     * @param g       ric record from information
     * @param address address info
     */
    @Override
    public abstract void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address);

    abstract List<InetSocketAddress> getAddressBase(GunRicInterfaceBuffer.GunRicCdtInterface g);

    public List<InetSocketAddress> getAddress(GunRicInterfaceBuffer.GunRicCdtInterface gunRicCdtInterface) {
        List<InetSocketAddress> addresses;
        if (lastRecord != null && (addresses = lastRecord.getAddress(gunRicCdtInterface)) != null) {
            return addresses;
        } else {
            return getAddressBase(gunRicCdtInterface);
        }
    }
}
