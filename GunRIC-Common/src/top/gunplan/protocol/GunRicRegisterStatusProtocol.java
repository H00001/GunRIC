package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @since 0.0.0.1
 */
public class GunRicRegisterStatusProtocol extends AbstractGunRicProtocol {
    public GunRicRegisterStatusProtocol() {
        this.type = RicProtocolType.REGRESP;
    }

    public GunRicRegisterStatusProtocol(RicProtocolCode status) {
        this();
        this.code = status;

    }

    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        publicUnSet(util);
        return checkEnd(util) && checKNext(in, util);
    }


    @Override
    public byte[] serialize() {
        byte[] sv = new byte[TYPE_LEN + CODE_LEN + SERIALNUM_LEN + END_FLAG.length];
        GunBytesUtil.GunWriteByteUtil util = new GunBytesUtil.GunWriteByteUtil(sv);
        publicSet(util);
        util.write(END_FLAG);
        return sv;
    }
}
