package top.gunplan.ric.protocol;

/**
 * @author dosdrtt
 */

public enum RicProtocolType {
    /**
     * REQUEST request packet (interface invoke request)
     * RESPONSE response packet (send invoke function result)
     * REGISTER register provider
     * REGRESP response the provider
     * GET user send it to center to get interface provider
     * PING hello message
     * PONG reply PING (hello message)
     */
    REQUEST(0x01), RESPONSE(0x02), REGISTER(0x03),
    REGRESP(0x04), GET(0x08), HELLO(0x09), NONE(0x30);
    int value;

    RicProtocolType(int i) {
        this.value = i;
    }

    public static RicProtocolType valuefrom(int val) {
        RicProtocolType[] types = values();
        for (RicProtocolType tp : types) {
            if (tp.value == val) {
                return tp;
            }
        }
        return NONE;
    }
}
