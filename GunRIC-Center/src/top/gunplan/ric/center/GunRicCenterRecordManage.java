package top.gunplan.ric.center;

import top.gunplan.ric.center.record.AbstractGunRicProxyRecord;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.net.InetSocketAddress;

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
    boolean isFirst(GunRicInterfaceBuffer.GunRicCdtInterface g);

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
     * @param registerRegex GunRicCenterRecord
     */
    void registerFirst(GunRicCenterRecord registerRegex);


    /**
     * start Regex
     *
     * @param g       GunRicInterfaceBuffer.GunRicCdtInterface
     * @param address InetSocketAddress
     */
    void doRegex(final GunRicInterfaceBuffer.GunRicCdtInterface g, final GunRicRespAddressProtocol.AddressItem address);


    /**
     * @return AbstractGunRicProxyRecord
     * @apiNote p
     */
    AbstractGunRicProxyRecord getFirstRecord();

}
