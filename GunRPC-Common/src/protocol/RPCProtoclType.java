package protocol;

/**
 * @author dosdrtt
 */

public enum RPCProtoclType {
    /**
     *
     */
    REQUEST(0x01), RESPONSE(0x02),REGISTER(0x03),REGRESP(0x04);
    int value;

    RPCProtoclType(int i) {
        this.value = i;
    }

    public static RPCProtoclType valuefrom(int val) {
        RPCProtoclType[] types = values();
        for (RPCProtoclType tp : types) {
            if (tp.value == val) {
                return tp;
            }
        }
        return null;
    }
}
