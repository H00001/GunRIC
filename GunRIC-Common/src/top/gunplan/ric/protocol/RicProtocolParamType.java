package top.gunplan.ric.protocol;

import top.gunplan.ric.anno.FieldMap;
import top.gunplan.ric.common.F;

import java.lang.reflect.Field;
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
    @FieldMap(mapc = Integer.class)
    INT((byte) 0x01, int.class, 4, 0),
    @FieldMap(mapc = Short.class)
    SHORT((byte) 0x09, short.class, 2, 0),
    @FieldMap(mapc = Long.class)
    LONG((byte) 0x0c, long.class, 8, 0),
    @FieldMap(mapc = Boolean.class)
    BOOLEAN((byte) 0x03, boolean.class, 1, 0),
    @FieldMap(mapc = Byte.class)
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

    /**
     * Mmap
     */
    static class Mmap {
        static Map<Class<?>, RicProtocolParamType> mmap = new HashMap<>();

        static {
            for (Field fd : RicProtocolParamType.class.getFields()) {
                FieldMap map = fd.getAnnotation(FieldMap.class);
                if (map != null) {
                    try {
                        mmap.put(map.mapc(), (RicProtocolParamType) fd.get(null));
                    } catch (IllegalAccessException e) {
                        F.LOG.error(e);
                    }
                    //  mmap.put()
                }
            }



        }
    }
}


