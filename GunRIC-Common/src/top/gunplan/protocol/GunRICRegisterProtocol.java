package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.0
 * @since 0.0.0.0
 */
public class GunRICRegisterProtocol extends AbstractGunRPCExecuteProtocol {
    private int paramlen = 0;

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

    public GunRICRegisterProtocol() {
        this.type = RPCProtoclType.REGISTER;
    }

    public GunRICRegisterProtocol(int port) {
        this.port = port;
    }

    public int getParamlen() {
        return paramlen;
    }

    public void setParamlen(int paramlen) {
        this.paramlen = paramlen;
        types = new Class<?>[paramlen];
    }

    public void pushParamType(Class<?> type) {
        this.types[now++] = type;
    }

    private void readParam(int paramlen0, GunBytesUtil.GunReadByteUtil util) {
        if (paramlen0 != 0) {
            for (int i = 0; i < paramlen0; i++) {
                types[i] = RPCProtoclParamType.valuefrom(util.readByte()).clazz;
            }
        }
    }

    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        this.type = RPCProtoclType.valuefrom(util.readInt());
        this.port = util.readInt();
        super.stdHeadAnaly(util);
        this.paramlen = util.readByte();
        types = new Class<?>[paramlen];
        readParam(paramlen, util);
        return checkEnd(util);
    }

    @Override
    public byte[] serialize() {
        int len = 7 + paramlen+2+interfaceName.length()+methodName.length();
        byte[] save = new byte[len];
        GunBytesUtil.GunWriteByteUtil util = new GunBytesUtil.GunWriteByteUtil(save);
        util.write(RPCProtoclType.REGISTER.value);
        util.write(port);
        super.stdHeadWrite(util);
        util.writeByte((byte) paramlen);
        writeParamTypes(util);
        util.write(endFlage);
        return save;
    }

    private void writeParamTypes(GunBytesUtil.GunWriteByteUtil util) {
        for (int i = 0; i < paramlen; i++) {
            util.writeByte(RPCProtoclParamType.valuefrom(types[i]).val);
        }
    }
}
