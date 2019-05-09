package top.gunplan.protocol;

import top.gunplan.netty.GunException;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @see GunException
 * @since 0.0.1.5
 */
class GunObjectCannotSerizableException extends GunException {
    private static final long serialVersionUID = -3559997432609348176L;

    GunObjectCannotSerizableException(String why) {
        super(why);
    }
}
