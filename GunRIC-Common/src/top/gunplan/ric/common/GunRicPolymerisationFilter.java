package top.gunplan.ric.common;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.filter.GunNettyInboundFilter;
import top.gunplan.netty.impl.checker.GunInboundChecker;

/**
 * GunRicPolymerisationFilter
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 11:25
 */
public interface GunRicPolymerisationFilter extends GunNettyInboundFilter {

    @Override
    default DealResult doInputFilter(GunInboundChecker checker) throws GunChannelException {
        return DealResult.NEXT;
    }
}
