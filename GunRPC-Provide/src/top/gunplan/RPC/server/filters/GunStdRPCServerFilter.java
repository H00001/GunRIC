package top.gunplan.RPC.server.filters;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.example.GunInputFilterChecker;
import top.gunplan.netty.impl.example.GunOutputFilterChecker;
import top.gunplan.netty.protocol.GunRPCInputProtocl;

@GunNetFilterOrder(index = 1)
public class GunStdRPCServerFilter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunInputFilterChecker gunInputFilterChecker) throws Exception {
        GunRPCInputProtocl protocl = new GunRPCInputProtocl();
        protocl.unSerialize(gunInputFilterChecker.getSrc());
        gunInputFilterChecker.setObject(protocl);
        return DealResult.NEXT;
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker gunOutputFilterChecker) throws Exception {
        return DealResult.NEXT;
    }
}
