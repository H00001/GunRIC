package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * AbstractCenterHelperProtocol
 *
 * @author dosdrtt
 * @see AbstractGunRicExecuteProtocol
 */
public abstract class AbstractCenterHelperProtocol extends AbstractGunRicExecuteProtocol {
    int paramcount = 0;
    private Class<?>[] types = null;
    private int now = 0;

    public void setParamcount(int paramcount) {
        this.paramcount = paramcount;
        types = new Class<?>[paramcount];
    }

    public Class<?>[] getTypes() {
        return types;
    }

    public int getParamcount() {
        return paramcount;
    }

    public void pushParamType(Class<?> type) {
        this.types[now++] = type;
    }

    public void pushParamTypes(Class<?>[] types) {
        this.setParamcount(types.length);
        for (Class<?> clazz : types) {
            pushParamType(clazz);
        }
    }

    void writeParamTypes(GunBytesUtil.GunWriteByteStream util) {
        util.writeByte((byte) paramcount);
        for (int i = 0; i < paramcount; i++) {
            util.writeByte(RicProtocolParamType.valuefrom(types[i]).val);
        }
    }

    public void clearParams() {
        now = 0;
        types = null;
    }

    void readParamType(GunBytesUtil.GunReadByteStream util) {
        this.paramcount = util.readByte();
        if (this.paramcount != 0) {
            types = new Class<?>[paramcount];
            for (int i = 0; i < this.paramcount; i++) {
                types[i] = RicProtocolParamType.valuefrom(util.readByte()).clazz;
            }
        }
    }
}
