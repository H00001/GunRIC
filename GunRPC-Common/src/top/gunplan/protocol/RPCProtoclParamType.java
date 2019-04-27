package top.gunplan.protocol;

public enum RPCProtoclParamType {
    /**
     *
     */
    INT((byte) 0x01, Integer.class, 4, 0), STRING((byte) 0x02, String.class, -1, 1), BOOLEAN((byte) 0x03, Byte.class, 1, 0), BYTE((byte) 0x05, byte.class, 1, 0), OBJECT((byte) 0x04, Object.class, -1, 0), ERROR((byte) 0x1c, Object.class, 0, 0);

    RPCProtoclParamType(byte val, Class<?> clazz, int stdlen, int deslen) {
        this.val = val;
        this.clazz = clazz;
        this.stdlen = stdlen;
        this.deslen = deslen;
    }

    int stdlen;
    int deslen;
    byte val;
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
        return ERROR;
    }

    public static RPCProtoclParamType valuefrom(Class<?> val) {
        RPCProtoclParamType[] types = values();
        for (RPCProtoclParamType tp : types) {
            if (tp.clazz == val) {
                return tp;
            }
        }
        return ERROR;
    }
}
