package top.gunplan.ric.user;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunExceptionType;

/**
 * @author dosdrtt
 * @date 2019/05/28
 */
public class GunRicUserReceiveException extends GunException {
    public GunRicUserReceiveException(String why) {
        super(GunExceptionType.EXC2, why);
    }
}
