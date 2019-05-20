package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @since 0.0.0.1
 */
public class GunRicRegisterStatusProtocol extends AbstractGunRicProtocol implements GunRicOutputHelper {
    public GunRicRegisterStatusProtocol() {
        this.type = RicProtocolType.REGRESP;
    }

    public GunRicRegisterStatusProtocol(int sericoode) {
        this();
        this.setSerialnumber(sericoode);
    }

    public GunRicRegisterStatusProtocol(RicProtocolCode status) {
        this();
        this.code = status;

    }


    @Override
    public byte[] serialize() {
        GunBytesUtil.GunWriteByteStream util = createSpace();
        publicSet(util);
        util.write(END_FLAG);
        return util.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        byte[] sv = new byte[TYPE_LEN + CODE_LEN + SERIALNUM_LEN + END_FLAG.length];
        return new GunBytesUtil.GunWriteByteStream(sv);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        return checkEnd(util) && checKNext(util);
    }
}
