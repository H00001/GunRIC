package top.gunplan.ric.center;

import top.gunplan.ric.center.record.AbstractGunRicProxyRecord;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;

/**
 * this class is used to record manage
 *
 * @author dosdrtt
 * @date 1558736561
 */
public interface GunRicCenterRecordManage {
    /**
     * check it is first record
     *
     * @param g record object
     * @return is or not
     */
    boolean isFirst(BaseGunRicCdt g);

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
     * @param g       GunRicInterfaceBuffer.BaseGunRicCdt
     * @param address InetSocketAddress
     */
    void doRegex(final BaseGunRicCdt g, final GunAddressItemInterface address);


    /**
     * getFirstRecord
     *
     * @return AbstractGunRicProxyRecord
     * @apiNote p
     */
    AbstractGunRicProxyRecord getFirstRecord();

}
