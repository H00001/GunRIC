package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * this is a new way to organize the protocol
 *
 * @author frank
 * @date 2019-06-27
 */
public interface GunRicProtocolBody {
    /**
     * fillData
     *
     * on protocol construct happened
     * {@link AbstractGunRicProtocol } will
     * called this method to construct message
     * body.
     * @param stream input stream
     * @return fill data result
     */
    boolean fillData(GunBytesUtil.GunWriteByteStream stream);

    /**
     * the message body need space to write
     * @return size
     */
    int needSpace();
}
