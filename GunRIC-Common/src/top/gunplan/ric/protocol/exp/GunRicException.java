package top.gunplan.ric.protocol.exp;

import top.gunplan.netty.GunException;

/**
 * will be Deprecated
 *
 * @author dosdrtt
 * kinds of models have theirs exception
 */

public class GunRicException extends GunException {

    private static final long serialVersionUID = 7522713377771117578L;

    public GunRicException(String why) {
        super(why);
    }
}
