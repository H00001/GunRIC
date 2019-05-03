package top.gunplan.RIC.center;


import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
import top.gunplan.protocol.AbstractGunRPCProtocl;
import top.gunplan.protocol.GunRPCDividePacketManage;

@GunNetFilterOrder(index = 1)
public class GunDubboCenterStdFilter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunInputFilterChecker filterDto) {
        AbstractGunRPCProtocl protocol = GunRPCDividePacketManage.findPackage(filterDto.getSrc());
        filterDto.setObject(protocol);
        return DealResult.NEXT;
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker filterDto) throws Exception {
        return null;
    }
}
