package top.gunplan.ric.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @since 0.0.0.1
 */
public class GunRicRegisterProtocol extends AbstractCenterHelperProtocol {

    public static final int ADDRESS_LEN = 4;
    private String ip;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    private int port;


    public GunRicRegisterProtocol() {
        this.type = RicProtocolType.REGISTER;
    }

    public GunRicRegisterProtocol(int port) {
        this.port = port;
    }


    @Override
    public byte[] serialize() {
        int len = 3 + CODE_LEN + ADDRESS_LEN + TYPE_LEN + SERIALNUM_LEN + paramcount + END_FLAG.length + interfaceName.length() + methodName.length();
        byte[] save = new byte[len];
        GunBytesUtil.GunWriteByteStream util = new GunBytesUtil.GunWriteByteStream(save);
        util.write(RicProtocolType.REGISTER.value);
        util.write(port);
        util.write(getSerialnumber());
        writeIpAddress4(util);
        super.stdHeadWrite(util);
        writeParamTypes(util);
        util.write(END_FLAG);
        return save;
    }

    private void writeIpAddress4(GunBytesUtil.GunWriteByteStream util) {
        String[] fg = this.ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            util.writeByte((byte) Integer.parseInt(fg[i]));
        }
    }

    private void readIpAddress4(GunBytesUtil.GunReadByteStream util) {
        short f1 = util.readUByte();
        short f2 = util.readUByte();
        short f3 = util.readUByte();
        short f4 = util.readUByte();
        this.ip = (f1) + "." + f2 + "." + f3 + "." + f4;
    }


    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        this.type = RicProtocolType.valuefrom(util.readInt());
        this.port = util.readInt();
        this.setSerialnumber(util.readInt());
        readIpAddress4(util);
        super.stdHeadAnaly(util);
        readParamType(util);
        return checkEnd(util) && checkNext(util);
    }
}
