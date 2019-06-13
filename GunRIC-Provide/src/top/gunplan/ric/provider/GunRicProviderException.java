package top.gunplan.ric.provider;

import top.gunplan.netty.GunException;

/**
 * GunRicProviderException
 *
 * @author dosdrtt
 */
public class GunRicProviderException extends GunException {
    private static final long serialVersionUID = 8682992677645496203L;
    private GunRicProviderErrorType type;

    public GunRicProviderException(String why, GunRicProviderErrorType type) {
        super(why);
        this.type = type;
    }

    public String getType() {
        return type.toString();
    }

    public enum GunRicProviderErrorType {
        /**
         *
         */
        INVOKE_ERROR(-1), OUT_OF_BOUND(-2), WRITE_OBJECT(-3);
        private int value;

        GunRicProviderErrorType(int value) {
            this.value = value;
        }
    }
}
