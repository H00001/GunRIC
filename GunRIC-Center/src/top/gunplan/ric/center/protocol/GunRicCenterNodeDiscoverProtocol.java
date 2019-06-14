package top.gunplan.ric.center.protocol;

import top.gunplan.utils.GunBytesUtil;

public class GunRicCenterNodeDiscoverProtocol extends AbstractGunRicCenterProtocol {
    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        return false;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }
}
