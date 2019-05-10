package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @since 0.0.0.1
 */
public class GunRicRegisterProtocol extends AbstractCenterHelperProtocol {

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


    public void setParamount(int paramount) {
        this.paramcount = paramount;
        types = new Class<?>[paramount];
    }


    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        this.type = RicProtocolType.valuefrom(util.readInt());
        this.port = util.readInt();
        this.setSerialnumber(util.readInt());
        super.stdHeadAnaly(util);
        readParam(util);
        return checkEnd(util);
    }

    @Override
    public byte[] serialize() {
        int len = 3 + CODE_LEN + TYPE_LEN + SERIALNUM_LEN + paramcount + END_FLAG.length + interfaceName.length() + methodName.length();
        byte[] save = new byte[len];
        GunBytesUtil.GunWriteByteUtil util = new GunBytesUtil.GunWriteByteUtil(save);
        util.write(RicProtocolType.REGISTER.value);
        util.write(port);
        util.write(getSerialnumber());
        super.stdHeadWrite(util);
        writeParamTypes(util);
        util.write(END_FLAG);
        return save;
    }


}
