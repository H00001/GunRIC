package top.gunplan.ric.center;

import top.gunplan.ric.center.record.AbstractGunRicProxyRecord;
import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;

/**
 * GunRICCenterRecordManager
 * <p>
 * this class is used to record manage
 *
 * @author dosdrtt
 * #date 1558736561
 */
public interface GunRICCenterRecordManager {
    /**
     * check it is first record
     *
     * @param g record object
     * @return is or not
     */
    boolean isFirst(BaseGunRicServerInformation g);

    /**
     * register the recorder
     *
     * @param registerRegex GunRicCenterRecord
     */
    void register(AbstractGunRicProxyRecord registerRegex);

    /**
     * register by loop way
     *
     * @param record AbstractGunRicProxyRecord
     */

    void registerLoop(AbstractGunRicProxyRecord record);

    /**
     * registerFirst first execute to register
     *
     * @param registerRegex GunRicCenterRecord
     */
    void registerFirst(GunRicCenterRecord registerRegex);


    /**
     * start Regex
     *
     * @param g       GunRicInterfaceBuffer.BaseGunRicServerInformation
     * @param address InetSocketAddress
     */
    void doRegex(final BaseGunRicServerInformation g, final GunAddressItemInterface address);


    /**
     * getFirstRecord
     *
     * @return AbstractGunRicProxyRecord
     * @apiNote p
     */
    AbstractGunRicProxyRecord getFirstRecord();


    /**
     * eraser
     *
     * @param address removed address
     */
    void eraser(final GunAddressItemInterface address);

}
