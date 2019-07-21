package top.gunplan.ric.common;

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
        if (params != null) {
            for (Class<?> paramType : params) {
                RicProtocolParamType tp = RicProtocolParamType.valuefrom(paramType);
                hashl += tp.val;
            }
        }
        return h | hashl + (params == null ? 0 : params.length);
    }

    private int hbase(final String base) {
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

