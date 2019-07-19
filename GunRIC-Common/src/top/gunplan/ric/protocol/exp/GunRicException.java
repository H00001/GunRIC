package top.gunplan.ric.protocol.exp;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunExceptionType;

/**
 * will be Deprecated
 *
 * @author dosdrtt
 * kinds of models have theirs exception
 */

public class GunRicException extends GunException {

    private static final long serialVersionUID = 7522713377771117578L;

    public GunRicException(String why) {
        super(GunExceptionType.EXC2, why);
    }
}
