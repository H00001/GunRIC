package top.gunplan.RPC.exp;

import top.gunplan.netty.GunException;

public class GunRPCException extends GunException {

    public GunRPCException(String why) {
        super(why);
    }
}
