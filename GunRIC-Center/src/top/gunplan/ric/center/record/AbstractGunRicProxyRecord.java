package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.GunAddressItem;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

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
    public abstract void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunAddressItem address);


    /**
     * @param g       ric record from information
     * @param address address info
     */
    @Override
    public abstract void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunAddressItem address);

    abstract List<GunAddressItem> getAddressBase(GunRicInterfaceBuffer.GunRicCdtInterface g);

    public List<GunAddressItem> getAddress(GunRicInterfaceBuffer.GunRicCdtInterface gunRicCdtInterface) {
        List<GunAddressItem> addresses;
        if (lastRecord != null && (addresses = lastRecord.getAddress(gunRicCdtInterface)) != null) {
            return addresses;
        } else {
            return getAddressBase(gunRicCdtInterface);
        }
    }
}
