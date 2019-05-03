package top.gunplan.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum RPCProtoclParamType {
    /**
     *
     */
    INT((byte) 0x01, int.class, 4, 0),
    SHORT((byte) 0x09, short.class, 2, 0),
    LONG((byte) 0x0a, long.class, 8, 0),
    BOOLEAN((byte) 0x03, boolean.class, 1, 0),
    BYTE((byte) 0x05, byte.class, 1, 0),
    STRING((byte) 0x02, String.class, -1, 1),
    LINT((byte) 0x06, int[].class, -1, 1),
    OBJECT((byte) 0x04, Object.class, -1, 1);


    RPCProtoclParamType(byte val, Class<?> clazz, int stdlen, int deslen) {
        this.val = val;
        this.clazz = clazz;
        this.stdlen = stdlen;
        this.deslen = deslen;
    }

    int stdlen;
    int deslen;
    public byte val;
    Class<?> clazz;


//    RPCProtoclParamType(byte val) {
//        this.val = val;
//    }

    Class<?> getClazz() {
        return this.clazz;
    }

    public static RPCProtoclParamType valuefrom(byte val) {
        RPCProtoclParamType[] types = values();
        for (RPCProtoclParamType tp : types) {
            if (tp.val == val) {
                return tp;
            }
        }
        return OBJECT;
    }

    public static RPCProtoclParamType valuefrom(Class<?> val) {
        RPCProtoclParamType tp = Mmap.mmap.get(val);
        if (tp == null) {
            RPCProtoclParamType[] types = values();
            for (RPCProtoclParamType tp1 : types) {
                if (tp1.clazz == val) {
                    return tp1;
                }
            }
        } else {
            return tp;
        }

        return OBJECT;
    }

    static class Mmap {
        static Map<Class<?>, RPCProtoclParamType> mmap = new HashMap<>();

        static {
            mmap.put(Integer.class, RPCProtoclParamType.INT);
            mmap.put(Boolean.class, RPCProtoclParamType.BOOLEAN);
            mmap.put(Byte.class, RPCProtoclParamType.BYTE);
            mmap.put(Integer[].class, RPCProtoclParamType.LINT);
            mmap.put(Short.class, RPCProtoclParamType.SHORT);
            mmap.put(Long.class, RPCProtoclParamType.LONG);
        }
    }
}


