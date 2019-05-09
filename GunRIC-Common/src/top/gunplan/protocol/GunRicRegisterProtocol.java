package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.0
 * @since 0.0.0.0
 */
public class GunRicRegisterProtocol extends AbstractGunRicExecuteProtocol {
    private int paramount = 0;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    private int port;
    private Class<?>[] types = null;
    private int now = 0;

    public void clearParams() {
        now = 0;
        types = null;
    }

    public GunRicRegisterProtocol() {
        this.type = RicProtocolType.REGISTER;
    }

    public GunRicRegisterProtocol(int port) {
        this.port = port;
    }

    public int getParamount() {
        return paramount;
    }

    public void setParamount(int paramount) {
        this.paramount = paramount;
        types = new Class<?>[paramount];
    }

    public void pushParamType(Class<?> type) {
        this.types[now++] = type;
    }

    private void readParam(int paramlen0, GunBytesUtil.GunReadByteUtil util) {
        if (paramlen0 != 0) {
            for (int i = 0; i < paramlen0; i++) {
                types[i] = RicProtocolParamType.valuefrom(util.readByte()).clazz;
            }
        }
    }

    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        this.type = RicProtocolType.valuefrom(util.readInt());
        this.port = util.readInt();
        this.setSerialnumber(util.readInt());
        super.stdHeadAnaly(util);
        this.paramount = util.readByte();
        types = new Class<?>[paramount];
        readParam(paramount, util);
        return checkEnd(util);
    }

    @Override
    public byte[] serialize() {
        int len = 3 + CODE_LEN + TYPE_LEN + SERIALNUM_LEN + paramount + END_FLAG.length + interfaceName.length() + methodName.length();
        byte[] save = new byte[len];
        GunBytesUtil.GunWriteByteUtil util = new GunBytesUtil.GunWriteByteUtil(save);
        util.write(RicProtocolType.REGISTER.value);
        util.write(port);
        util.write(getSerialnumber());
        super.stdHeadWrite(util);
        util.writeByte((byte) paramount);
        writeParamTypes(util);
        util.write(END_FLAG);
        return save;
    }

    private void writeParamTypes(GunBytesUtil.GunWriteByteUtil util) {
        for (int i = 0; i < paramount; i++) {
            util.writeByte(RicProtocolParamType.valuefrom(types[i]).val);
        }
    }
}
