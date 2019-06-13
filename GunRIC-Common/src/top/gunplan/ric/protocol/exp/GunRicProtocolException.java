package top.gunplan.ric.protocol.exp;

import top.gunplan.netty.GunException;

/**
 * this exception is used to ric provider
 *
 * @author dosdrtt
 */
public class GunRicProtocolException extends GunException {

    private static final long serialVersionUID = 916487049376902889L;

    private GunRicProtocolErrorType type;

    public GunRicProtocolException(String why, GunRicProtocolErrorType type) {
        super(why);
        this.type = type;
    }

    public GunRicProtocolErrorType getType() {
        return type;
    }

    public enum GunRicProtocolErrorType {
        /**
         *
         */
        WRITE_PARAM_ERROR(-1), OUT_OF_BOUND(-2), WRITE_OBJECT(-3),
        PROROCOL_INVIDA(-4);
        private int value;

        GunRicProtocolErrorType(int value) {
            this.value = value;
        }
    }
}
