package top.gunplan.RIC.center;

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
    void register(GunRicCenterRecord registerRegex);

    /**
     * registerFirst first execute to register
     * @param registerRegex GunRicCenterRecord
     */
    void registerFirst(GunRicCenterRecord registerRegex);


    void doRegex(final GunRicInterfaceBuffer.GunRicCdtInterface g, final InetSocketAddress address);
}
