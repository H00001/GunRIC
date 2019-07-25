package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Set;

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
    public abstract void firstAdd(BaseGunRicServerInformation g, GunAddressItemInterface address);


    /**
     * nextAdd
     * <p>
     * this method invoke at next record
     *
     * @param g       ric record from information
     * @param address address info
     */
    @Override
    public abstract void nextAdd(BaseGunRicServerInformation g, GunAddressItemInterface address);

    /**
     * get address
     *
     * @param g BaseGunRicServerInformation
     * @return list of item
     */
    abstract Set<GunAddressItemInterface> getAddressBase(BaseGunRicServerInformation g);

    public Set<GunAddressItemInterface> getAddress(BaseGunRicServerInformation gunRicCdtInterface) {
        Set<GunAddressItemInterface> addresses;
        if (lastRecord != null && (addresses = lastRecord.getAddress(gunRicCdtInterface)) != null) {
            return addresses;
        } else {
            return getAddressBase(gunRicCdtInterface);
        }
    }
}
