package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.0
 * @since 0.0.0.0
 */
public class GunRicRegisterStatusProtocol extends AbstractGunRPCProtocl {
    GunRicRegisterStatusProtocol() {

    }

    public GunRicRegisterStatusProtocol(RicProtoclCode status) {
        this.type = RicProtocolType.REGRESP;
        this.code = status;

    }

    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        publicUnSet(util);
        return checkEnd(util);
    }

    @Override
    public byte[] serialize() {
        byte[] sv = new byte[6];
        GunBytesUtil.GunWriteByteUtil util = new GunBytesUtil.GunWriteByteUtil(sv);
        publicSet(util);
        util.write(END_FLAG);
        return sv;
    }
}
