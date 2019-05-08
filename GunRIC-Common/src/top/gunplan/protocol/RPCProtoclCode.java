package top.gunplan.protocol;

/**
 * @author dosdrtt
 */

public enum RPCProtoclCode {
    /**
     *
     */
    SUCCEED(0x00), WARNING(0x02), NONE(0x05), FAIL(0x0a);
    int value;

    RPCProtoclCode(int i) {
        this.value = i;
    }

    public static RPCProtoclCode valuefrom(int val) {
        RPCProtoclCode[] types = values();
        for (RPCProtoclCode tp : types) {
            if (tp.value == val) {
                return tp;
            }
        }
        return null;
    }
}
