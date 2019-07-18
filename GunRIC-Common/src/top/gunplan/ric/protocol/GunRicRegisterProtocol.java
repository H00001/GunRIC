package top.gunplan.ric.protocol;


import top.gunplan.ric.stand.GunRicRegisterStand;
import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.2
 * @since 0.0.0.1
 */
public class GunRicRegisterProtocol extends AbstractCenterHelperProtocol implements GunRicRegisterStand {

    private GunAddressItemInterface item = new GunAddressItem4();

    public GunRicRegisterProtocol() {
        this.type = RicProtocolType.REGISTER;
        this.code = RicProtocolCode.SUCCEED;
    }

    public void setItem(GunAddressItemInterface item) {
        this.item = item;
    }

    @Override
    public String ipAddress() {
        return item.getAddress();
    }

    @Override
    public int portNumber() {
        return item.getPort();
    }

    @Override
    public byte[] serialize() {
        int len = 3 + CODE_LEN + GunAddressItem4.NEED_SPACE + TYPE_LEN + SERIALIZE_LEN + paramCount + END_FLAG.length + interfaceName.length() + methodName.length();
        byte[] save = new byte[len];
        GunBytesUtil.GunWriteByteStream util = new GunBytesUtil.GunWriteByteStream(save);
        publicSet(util);
        util.write(item.serialize());
        super.stdHeadWrite(util);
        writeParamTypes(util);
        util.write(END_FLAG);
        return save;
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        item.unSerialize(util);
        super.stdHeadAnaly(util);
        readParamType(util);
        return checkEnd(util) && checkNext(util);
    }
}
