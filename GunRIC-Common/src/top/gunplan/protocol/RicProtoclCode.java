package top.gunplan.protocol;

/**
 * @author dosdrtt
 */

public enum RicProtoclCode {
    /**
     *
     */
    SUCCEED(0x00), WARNING(0x02), NONE(0x05), HELLO_REQ(0x06), HELLO_RES(0x07), FAIL(0x0a);

    int value;

    RicProtoclCode(int i) {
        this.value = i;
    }

    public static RicProtoclCode valuefrom(int val) {
        RicProtoclCode[] types = values();
        for (RicProtoclCode tp : types) {
            if (tp.value == val) {
                return tp;
            }
        }
        return null;
    }
}
