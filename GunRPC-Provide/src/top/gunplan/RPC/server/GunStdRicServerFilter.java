package top.gunplan.RPC.server;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;

import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
import top.gunplan.protocol.GunRicInputProtocol;


/**
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 1)
public class GunStdRicServerFilter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunInputFilterChecker gunInputFilterChecker) {
        GunRicInputProtocol protocl = new GunRicInputProtocol();
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
