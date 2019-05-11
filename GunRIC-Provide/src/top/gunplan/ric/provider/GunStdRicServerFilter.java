package top.gunplan.ric.provider;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;

import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
import top.gunplan.ric.protocol.GunRicInputProtocol;
import static top.gunplan.netty.GunNettyFilter.DealResult.*;

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
            return NEXT;
        } else {
            return NOTDEALALLNEXT;
        }
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker gunOutputFilterChecker) {
        return NEXT;
    }
}
