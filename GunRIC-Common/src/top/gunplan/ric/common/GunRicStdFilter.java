package top.gunplan.ric.common;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunNettyInputFilterChecker;
import top.gunplan.netty.impl.GunNettyOutputFilterChecker;
import top.gunplan.ric.protocol.AbstractGunRicProtocol;
import top.gunplan.ric.protocol.GunRicTypeDividePacketManage;
import top.gunplan.utils.AbstractGunBaseLogUtil;
import static top.gunplan.netty.GunNettyFilter.DealResult.NEXT;
import static top.gunplan.netty.GunNettyFilter.DealResult.NOTDEALALLNEXT;

/**
 * GunRicStdFilter
 *
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 1)
public class GunRicStdFilter implements GunNettyFilter {

    @Override
    public DealResult doInputFilter(GunNettyInputFilterChecker gunInputFilterChecker) {
        AbstractGunRicProtocol protocol = GunRicTypeDividePacketManage.findPackage(gunInputFilterChecker.getSrc());
        if (protocol.unSerialize(gunInputFilterChecker.getSrc())) {
            gunInputFilterChecker.setTransfer(protocol);
            return NEXT;
        } else {
            AbstractGunBaseLogUtil.urgency("protocol unSerialize fail", getClass().getName());
            return NOTDEALALLNEXT;
        }
    }

    @Override
    public DealResult doOutputFilter(GunNettyOutputFilterChecker gunOutputFilterChecker) {
        return NEXT;
    }
}
