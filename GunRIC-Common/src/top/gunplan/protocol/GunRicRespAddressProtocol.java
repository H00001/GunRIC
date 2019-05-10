package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

public class GunRicRespAddressProtocol extends AbstractGunRicProtocol implements GunRicOutputHelper {
    public GunRicRespAddressProtocol() {
        this.type = RicProtocolType.GET;
        this.code = RicProtocolCode.GET_RES;
    }

    @Override
    public boolean unSerialize(byte[] bytes) {

        return false;
    }

    @Override
    public byte[] serialize() {
        GunBytesUtil.GunWriteByteUtil util = createSpace();
        return new byte[0];
    }

    @Override
    public GunBytesUtil.GunWriteByteUtil createSpace() {
        return null;
    }
}
