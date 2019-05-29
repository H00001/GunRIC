package top.gunplan.ric.protocol;

/**
 * @author dosdrtt
 */

public enum RicProtocolCode {
    /**
     *
     */
    SUCCEED(0x00), WARNING(0x02), NONE(0x05),
    GET_REQ(0x0b), GET_RES(0x0c),
    HELLO_REQ(0x06), HELLO_RES(0x07), FAIL(0x0a);

    public int value;

    RicProtocolCode(int i) {
        this.value = i;
    }

    public static RicProtocolCode valuefrom(int val) {
        RicProtocolCode[] types = values();
        for (RicProtocolCode tp : types) {
            if (tp.value == val) {
                return tp;
            }
        }
        return NONE;
    }
}
