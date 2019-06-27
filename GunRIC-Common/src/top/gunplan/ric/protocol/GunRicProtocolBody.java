package top.gunplan.ric.protocol;

/**
 * this is a new way to organize the protocol
 *
 * @author frank
 * @date 2019-06-27
 */
public interface GunRicProtocolBody {
    /**
     * on protocol construct happened
     * {@link AbstractGunRicProtocol } will
     * called this method to construct message
     * body.
     *
     * @param stream
     */
    boolean fillData(GunBytesUtil.GunWriteByteStream stream);

    /**
     * the message body need space to write
     * @return size
     */
    int needSpace();
}
