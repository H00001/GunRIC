package top.gunplan.ric.common;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.filter.GunNettyInboundFilter;
import top.gunplan.netty.filter.GunNettyOutboundFilter;
import top.gunplan.netty.impl.checker.GunInboundChecker;
import top.gunplan.netty.impl.checker.GunOutboundChecker;
import top.gunplan.ric.protocol.GunRicTypeDividePacketManage;
import top.gunplan.ric.stand.GunRicBaseStand;

import static top.gunplan.netty.filter.GunNettyFilter.DealResult.NEXT;

/**
 * GunRicStdFilter
 *
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 1)
public class GunRicStdFilter implements GunNettyInboundFilter, GunNettyOutboundFilter {

//    @Override
//    public DealResult doInputFilter(GunNettyInputFilterChecker gunInputFilterChecker) {
//        GunRicBaseStand protocol = GunRicTypeDividePacketManage.findPackage(gunInputFilterChecker.source());
//        if (protocol.unSerialize(gunInputFilterChecker.source())) {
//            gunInputFilterChecker.setTransfer(protocol);
//            return NEXT;
//        } else {
//            F.LOG.urgency("protocol unSerialize fail", getClass().getName());
//            return NOTDEALALLNEXT;
//        }
//    }


    @Override
    public DealResult doInputFilter(GunInboundChecker filterDto) throws GunChannelException {
        GunRicBaseStand protocol = GunRicTypeDividePacketManage.findPackage(filterDto.source());
        if (protocol.unSerialize(filterDto.source())) {
            filterDto.setTransfer(protocol);
            return NEXT;
        } else {
            F.LOG.urgency("protocol unSerialize fail", getClass().getName());
            filterDto.channel().closeAndRemove(true);
            return DealResult.CLOSED;
        }
    }


    @Override
    public DealResult doOutputFilter(GunOutboundChecker gunOutboundChecker) throws GunChannelException {
        return NEXT;
    }
}

