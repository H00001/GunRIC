package top.gunplan.ric.center.common;

import top.gunplan.ric.protocol.RicProtocolParamType;

/**
 * @author dos
 */
public final class GunRicMethodHash implements GunRicHash {
    @Override
    public final int h(final String interfaceName, final String methodName, Class<?>[] params) {
        int h = 0;
        h = h | interfaceName.length() << 24;
        h = h | hbase(interfaceName) << 16;
        h = h | hbase(methodName) << 8;
        int hashl = 0;
        for (Class<?> paramtype : params) {
            RicProtocolParamType tp = RicProtocolParamType.valuefrom(paramtype);
            hashl += tp.val;
        }
        return h | hashl + params.length;


    }

    public final int hbase(final String base) {
        int h = 0;
        for (int i = 0; i < base.length(); i++) {
            h += base.charAt(i);
        }
        return h;
    }

    public static class Instance {
        private static final GunRicMethodHash GUN_RIC_METHOD_HASH = new GunRicMethodHash();

        public static GunRicHash getHashInstance() {
            return GUN_RIC_METHOD_HASH;
        }
    }
}