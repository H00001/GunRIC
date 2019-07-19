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
        GunRicCombineOutput opt = new GunRicCombineOutput();
        GunRicBaseStand std = (GunRicBaseStand) gunNettyOutputFilterChecker.getTransfer();
        opt.push(std);
        do {
            opt.push(std);
        }
        while ((std = std.next()) != null);
        gunNettyOutputFilterChecker.setTransfer(opt);
        return DealResult.NEXT;
    }
}
