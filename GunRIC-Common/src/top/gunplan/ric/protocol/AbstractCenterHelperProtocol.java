package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

import java.lang.reflect.Method;

/**
 * AbstractCenterHelperProtocol
 *
 * @author dosdrtt
 * @see AbstractGunRicExecuteProtocol
 */
public abstract class AbstractCenterHelperProtocol extends AbstractGunRicExecuteProtocol {
    int paramCount = 0;
    private Class<?>[] types = null;
    private int now = 0;

    public int getParamcount() {
        return paramCount;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    public void setParamcount(int paramcount) {
        this.paramCount = paramcount;
        types = new Class<?>[paramcount];
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
        util.writeByte((byte) paramCount);
        for (int i = 0; i < paramCount; i++) {
            util.writeByte(RicProtocolParamType.valuefrom(types[i]).val);
        }
    }

    public void clearParams() {
        now = 0;
        types = null;
    }


    public void setInameMnameAndParam(Method method) {
        this.setInameMname(method);
        this.pushParamTypes(method.getParameterTypes());

    }
    void readParamType(GunBytesUtil.GunReadByteStream util) {
        this.paramCount = util.readByte();
        if (this.paramCount != 0) {
            types = new Class<?>[paramCount];
            for (int i = 0; i < this.paramCount; i++) {
                types[i] = RicProtocolParamType.valuefrom(util.readByte()).clazz;
            }
        }
    }

}
