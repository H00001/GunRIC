package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;

import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;

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
    public abstract void firstAdd(BaseGunRicCdt g, GunAddressItemInterface address);


    /**
     * nextAdd
     * <p>
     * this method invoke at next record
     *
     * @param g       ric record from information
     * @param address address info
     */
    @Override
    public abstract void nextAdd(BaseGunRicCdt g, GunAddressItemInterface address);

    /**
     * get address
     *
     * @param g BaseGunRicCdt
     * @return list of item
     */
    abstract List<GunAddressItemInterface> getAddressBase(BaseGunRicCdt g);

    public List<GunAddressItemInterface> getAddress(BaseGunRicCdt gunRicCdtInterface) {
        List<GunAddressItemInterface> addresses;
        if (lastRecord != null && (addresses = lastRecord.getAddress(gunRicCdtInterface)) != null) {
            return addresses;
        } else {
            return getAddressBase(gunRicCdtInterface);
        }
    }
}
