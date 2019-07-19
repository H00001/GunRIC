package top.gunplan.ric.common;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunNettyOutputFilterChecker;
import top.gunplan.ric.protocol.GunRicCombineOutput;
import top.gunplan.ric.stand.GunRicBaseStand;

@GunNetFilterOrder(index = 2)
public class GunRicStdPolymerisationFilter implements GunRicPolymerisationFilter {
    @Override
    public DealResult doOutputFilter(GunNettyOutputFilterChecker gunNettyOutputFilterChecker) throws GunChannelException {
        GunRicBaseStand std = (GunRicBaseStand) gunNettyOutputFilterChecker.getTransfer();
        if (std.next() != null) {
            GunRicCombineOutput opt = new GunRicCombineOutput();
            for (; std != null; std = std.next()) {
                opt.push(std);
            }
            gunNettyOutputFilterChecker.setTransfer(opt);
            gunNettyOutputFilterChecker.translate();
        }
        return DealResult.NEXT;
    }
}
