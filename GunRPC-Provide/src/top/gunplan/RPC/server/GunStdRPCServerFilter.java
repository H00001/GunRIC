package top.gunplan.RPC.server;

import netty.GunNettyFilter;
import netty.anno.GunNetFilterOrder;
import netty.impl.GunInputFilterChecker;
import netty.impl.GunOutputFilterChecker;
import protocol.GunRPCInputProtocl;

/**
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 1)
public class GunStdRPCServerFilter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunInputFilterChecker gunInputFilterChecker) {
        GunRPCInputProtocl protocl = new GunRPCInputProtocl();
        if (protocl.unSerialize(gunInputFilterChecker.getSrc())) {
            gunInputFilterChecker.setObject(protocl);
            return DealResult.NEXT;
        } else {
            return DealResult.NOTDEALALLNEXT;
        }
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker gunOutputFilterChecker) {
        return DealResult.NEXT;
    }
}
