package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCdtInterface;
import top.gunplan.ric.center.GunRicCenterRecord;

import top.gunplan.ric.protocol.GunAddressItem;

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
    public abstract void firstAdd(GunRicCdtInterface g, GunAddressItem address);


    /**
     * nextAdd
     *
     * this method invoke at next record
     * @param g       ric record from information
     * @param address address info
     */
    @Override
    public abstract void nextAdd(GunRicCdtInterface g, GunAddressItem address);

    /**
     * get address
     *
     * @param g GunRicCdtInterface
     * @return list of item
     */
    abstract List<GunAddressItem> getAddressBase(GunRicCdtInterface g);

    public List<GunAddressItem> getAddress(GunRicCdtInterface gunRicCdtInterface) {
        List<GunAddressItem> addresses;
        if (lastRecord != null && (addresses = lastRecord.getAddress(gunRicCdtInterface)) != null) {
            return addresses;
        } else {
            return getAddressBase(gunRicCdtInterface);
        }
    }
}
