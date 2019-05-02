package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @version 0.0.0.0
 * @since 0.0.0.0
 */
public class GunRPCRegisterProtocol extends AbstractGunRPCProtocl {
    private int paramlen = 0;
    private int port;
    private Class<?>[] types = null;
    private int now = 0;

    public GunRPCRegisterProtocol() {

    }

    public GunRPCRegisterProtocol(int port) {
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

    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(in);
        this.type = RPCProtoclType.valuefrom(util.readInt());
        this.port = util.readInt();
        this.paramlen = util.readByte();
        if (paramlen != 0) {
            for (int i = 0; i < paramlen; i++) {
                types[i] = RPCProtoclParamType.valuefrom(util.readByte()).clazz;
            }
        }
        return checkEnd(util);
    }

    @Override
    public byte[] serialize() {
        int len = 7 + paramlen;
        byte[] save = new byte[len];
        GunBytesUtil.GunWriteByteUtil util = new GunBytesUtil.GunWriteByteUtil(save);
        util.write(RPCProtoclType.REGISTER.value);
        util.write(port);
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
