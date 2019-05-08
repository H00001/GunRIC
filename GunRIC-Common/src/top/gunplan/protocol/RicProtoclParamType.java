package top.gunplan.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dosdrtt
 * @date 1557304134
 */
public enum RicProtoclParamType {
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
    OBJECT((byte) 0x04, Object.class, -1, 1),
    LLINT((byte) 0x0b, int[][].class, -1, 2);


    RicProtoclParamType(byte val, Class<?> clazz, int stdlen, int deslen) {
        this.val = val;
        this.clazz = clazz;
        this.stdlen = stdlen;
        this.deslen = deslen;
    }

    int stdlen;
    int deslen;
    public byte val;
    public Class<?> clazz;


//    RicProtoclParamType(byte val) {
//        this.val = val;
//    }

    Class<?> getClazz() {
        return this.clazz;
    }

    public static RicProtoclParamType valuefrom(byte val) {
        RicProtoclParamType[] types = values();
        for (RicProtoclParamType tp : types) {
            if (tp.val == val) {
                return tp;
            }
        }
        return OBJECT;
    }

    public static RicProtoclParamType valuefrom(Class<?> val) {
        RicProtoclParamType tp = Mmap.mmap.get(val);
        if (tp == null) {
            RicProtoclParamType[] types = values();
            for (RicProtoclParamType tp1 : types) {
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
        static Map<Class<?>, RicProtoclParamType> mmap = new HashMap<>();

        static {
            mmap.put(Integer.class, RicProtoclParamType.INT);
            mmap.put(Boolean.class, RicProtoclParamType.BOOLEAN);
            mmap.put(Byte.class, RicProtoclParamType.BYTE);
            mmap.put(Integer[].class, RicProtoclParamType.LINT);
            mmap.put(Short.class, RicProtoclParamType.SHORT);
            mmap.put(Long.class, RicProtoclParamType.LONG);
            mmap.put(Integer[][].class, RicProtoclParamType.LLINT);
        }
    }
}


