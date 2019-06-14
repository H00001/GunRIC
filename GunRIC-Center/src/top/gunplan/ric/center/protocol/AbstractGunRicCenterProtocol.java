package top.gunplan.ric.center.protocol;

import top.gunplan.ric.protocol.AbstractGunRicProtocol;
import top.gunplan.utils.GunBytesUtil;

/**
 * AbstractGunRicCenterProtocol
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-14 17:57
 */
public abstract class AbstractGunRicCenterProtocol extends AbstractGunRicProtocol {
    @Override
    public abstract boolean unSerialize(GunBytesUtil.GunReadByteStream util);


    @Override
    public abstract byte[] serialize();

}
