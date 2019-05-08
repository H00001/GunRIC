package top.gunplan.protocol;

/**
 * @author dosdrtt
 */

public enum RicProtoclType {
    /**
     * REQUEST request packet (interface invoke request)
     * RESPONSE
     * REGISTER
     * REGRESP
     * GET
     * PING hello message
     * PONG reply PING (hello message)
     */
    REQUEST(0x01), RESPONSE(0x02), REGISTER(0x03),
    REGRESP(0x04),GET(0x08), HELLO(0x09);
    int value;

    RicProtoclType(int i) {
        this.value = i;
    }

    public static RicProtoclType valuefrom(int val) {
        RicProtoclType[] types = values();
        for (RicProtoclType tp : types) {
            if (tp.value == val) {
                return tp;
            }
        }
        return null;
    }
}
