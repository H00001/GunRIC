package top.gunplan.RIC.center.record;

import top.gunplan.netty.GunException;

/**
 * @author dosdrtt
 */
class GunRicCenterRecordFailException extends GunException {
    private static final long serialVersionUID = 3158361294733465033L;

    GunRicCenterRecordFailException(String why) {
        super(why);
    }
}
