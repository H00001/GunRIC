package top.gunplan.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * AbstractCenterHelperProtocol
 *
 * @author dosdrtt
 * @see AbstractGunRicExecuteProtocol
 */
abstract class AbstractCenterHelperProtocol extends AbstractGunRicExecuteProtocol {
    int paramcount = 0;
    Class<?>[] types = null;
    private int now = 0;

    public Class<?>[] getTypes() {
        return types;
    }

    public int getParamcount() {
        return paramcount;
    }

    public void pushParamType(Class<?> type) {
        this.types[now++] = type;
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

    void readParam(GunBytesUtil.GunReadByteStream util) {
        this.paramcount = util.readByte();
        if (this.paramcount != 0) {
            types = new Class<?>[paramcount];
            for (int i = 0; i < this.paramcount; i++) {
                types[i] = RicProtocolParamType.valuefrom(util.readByte()).clazz;
            }
        }
    }
}
