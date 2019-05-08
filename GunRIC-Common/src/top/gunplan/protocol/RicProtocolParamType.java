package top.gunplan.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dosdrtt
 * @date 1557304134
 */
public enum RicProtocolParamType {
    /**
     *
     */
    INT((byte) 0x01, int.class, 4, 0),
    SHORT((byte) 0x09, short.class, 2, 0),
    LONG((byte) 0x0c, long.class, 8, 0),
    BOOLEAN((byte) 0x03, boolean.class, 1, 0),
    BYTE((byte) 0x05, byte.class, 1, 0),
    STRING((byte) 0x02, String.class, -1, 1),
    LINT((byte) 0x06, int[].class, -1, 1),
    OBJECT((byte) 0x04, Object.class, -1, 2),
    LLINT((byte) 0x0b, int[][].class, -1, 2);


    RicProtocolParamType(byte val, Class<?> clazz, int stdlen, int deslen) {
        this.val = val;
        this.clazz = clazz;
        this.stdlen = stdlen;
        this.deslen = deslen;
    }

    int stdlen;
    int deslen;
    public byte val;
    public Class<?> clazz;


//    RicProtocolParamType(byte val) {
//        this.val = val;
//    }

    Class<?> getClazz() {
        return this.clazz;
    }

    public static RicProtocolParamType valuefrom(byte val) {
        RicProtocolParamType[] types = values();
        for (RicProtocolParamType tp : types) {
            if (tp.val == val) {
                return tp;
            }
        }
        return OBJECT;
    }

    public static RicProtocolParamType valuefrom(Class<?> val) {
        RicProtocolParamType tp = Mmap.mmap.get(val);
        if (tp == null) {
            RicProtocolParamType[] types = values();
            for (RicProtocolParamType tp1 : types) {
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
        static Map<Class<?>, RicProtocolParamType> mmap = new HashMap<>();

        static {
            mmap.put(Integer.class, RicProtocolParamType.INT);
            mmap.put(Boolean.class, RicProtocolParamType.BOOLEAN);
            mmap.put(Byte.class, RicProtocolParamType.BYTE);
            mmap.put(Integer[].class, RicProtocolParamType.LINT);
            mmap.put(Short.class, RicProtocolParamType.SHORT);
            mmap.put(Long.class, RicProtocolParamType.LONG);
            mmap.put(Integer[][].class, RicProtocolParamType.LLINT);
        }
    }
}


