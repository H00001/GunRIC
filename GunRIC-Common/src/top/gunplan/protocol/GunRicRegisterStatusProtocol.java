package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.0
 * @since 0.0.0.0
 */
public class GunRicRegisterStatusProtocol extends AbstractGunRPCProtocol {
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
        boolean isTrueSeria = checkEnd(util);
        boolean nextTrueSeria = true;
        if (in.length - util.getNowflag() > 8) {
            byte[] nextp = new byte[in.length - util.getNowflag()];
            System.arraycopy(in, util.getNowflag(), nextp, 0, nextp.length);
            AbstractGunRPCProtocol protocol = GunRicDividePacketManage.findPackage(nextp);
            nextTrueSeria = protocol.unSerialize(nextp);
            setNext(protocol);
        }
        return isTrueSeria && nextTrueSeria;
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
