package top.gunplan.ric.center.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * GunRicCenterHandSharkProtocol
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-14 17:55
 */
public class GunRicCenterHandSharkProtocol extends AbstractGunRicCenterProtocol {

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        return false;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }
}
