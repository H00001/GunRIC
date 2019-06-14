package top.gunplan.ric.center.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * GunRicCenterMessageProtocol
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-14 17:59
 */
public class GunRicCenterMessageProtocol extends AbstractGunRicCenterProtocol {
    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        return false;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }
}
