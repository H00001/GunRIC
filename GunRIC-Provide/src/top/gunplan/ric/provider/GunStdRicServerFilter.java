package top.gunplan.ric.provider;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
import top.gunplan.ric.protocol.GunRicInputProtocol;
import static top.gunplan.netty.GunNettyFilter.DealResult.*;

/**
 * @author dosdrtt
 * @deprecated
 */

@Deprecated
@GunNetFilterOrder(index = 1)
public class GunStdRicServerFilter implements GunNettyFilter {
    @Override
    @Deprecated
    public DealResult doInputFilter(GunInputFilterChecker gunInputFilterChecker) {
        GunRicInputProtocol protocol = new GunRicInputProtocol();
        if (protocol.unSerialize(gunInputFilterChecker.getSrc())) {
            gunInputFilterChecker.setObject(protocol);
            return NEXT;
        } else {
            return NOTDEALALLNEXT;
        }
    }

    @Deprecated
    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker gunOutputFilterChecker) {
        return NEXT;
    }
}
