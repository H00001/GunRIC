package top.gunplan.ric.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @since 0.0.0.1
 */
public class GunRicRegisterProtocol extends AbstractCenterHelperProtocol {

    private GunRicRespAddressProtocol.AddressItem item = new GunRicRespAddressProtocol.AddressItem();

    public GunRicRegisterProtocol() {
        this.type = RicProtocolType.REGISTER;
        this.code = RicProtocolCode.SUCCEED;
    }

    public void setItem(GunRicRespAddressProtocol.AddressItem item) {
        this.item = item;
    }

    public String getIp() {
        return item.getAddress();
    }

    public int getPort() {
        return item.getPort();
    }

    @Override
    public byte[] serialize() {
        int len = 3 + CODE_LEN + GunRicRespAddressProtocol.AddressItem.NEED_SPACE + TYPE_LEN + SERIALNUM_LEN + paramcount + END_FLAG.length + interfaceName.length() + methodName.length();
        byte[] save = new byte[len];
        GunBytesUtil.GunWriteByteStream util = new GunBytesUtil.GunWriteByteStream(save);
        publicSet(util);
        util.write(item.serialize());
        super.stdHeadWrite(util);
        writeParamTypes(util);
        util.write(END_FLAG);
        return save;
    }

//    private void writeIpAddress4(GunBytesUtil.GunWriteByteStream util) {
//        String[] fg = this.ip.split("\\.");
//        for (int i = 0; i < 4; i++) {
//            util.writeByte((byte) Integer.parseInt(fg[i]));
//        }
//    }

//    private void readIpAddress4(GunBytesUtil.GunReadByteStream util) {
//        short f1 = util.readUByte();
//        short f2 = util.readUByte();
//        short f3 = util.readUByte();
//        short f4 = util.readUByte();
//        this.ip = (f1) + "." + f2 + "." + f3 + "." + f4;
//    }


    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        item.unSerialize(util);
        super.stdHeadAnaly(util);
        readParamType(util);
        return checkEnd(util) && checkNext(util);
    }
}
