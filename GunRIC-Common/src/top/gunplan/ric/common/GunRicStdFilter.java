package top.gunplan.ric.common;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
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
    public DealResult doInputFilter(GunInputFilterChecker gunInputFilterChecker) {
        try {

            AbstractGunRicProtocol protocol = GunRicTypeDividePacketManage.findPackage(gunInputFilterChecker.getSrc());
            if (protocol.unSerialize(gunInputFilterChecker.getSrc())) {
                gunInputFilterChecker.setObject(protocol);
                return NEXT;
            } else {
                AbstractGunBaseLogUtil.urgency("protocol unSerialize fail", getClass().getName());
                return NOTDEALALLNEXT;
            }
        } catch (Exception exp) {
            AbstractGunBaseLogUtil.urgency("protocol unSerialize fail", getClass().getName());
            return NOTDEALALLNEXT;
        }
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker gunOutputFilterChecker) throws Exception {
        return NEXT;
    }
}
