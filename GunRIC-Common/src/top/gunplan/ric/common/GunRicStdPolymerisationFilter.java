package top.gunplan.ric.common;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.checker.GunOutboundChecker;
import top.gunplan.ric.protocol.GunRicCombineOutput;
import top.gunplan.ric.stand.GunRicBaseStand;

@GunNetFilterOrder(index = 2)
public class GunRicStdPolymerisationFilter implements GunRicPolymerisationFilter {

    @Override
    public DealResult doOutputFilter(GunOutboundChecker checker) throws GunChannelException {
        GunRicBaseStand std = (GunRicBaseStand) checker.transferTarget();
        if (std.next() != null) {
            GunRicCombineOutput opt = new GunRicCombineOutput();
            for (; std != null; std = std.next()) {
                opt.push(std);
            }
            checker.setTransfer(opt);
            checker.translate();
        }
        return DealResult.NEXT;
    }
}
