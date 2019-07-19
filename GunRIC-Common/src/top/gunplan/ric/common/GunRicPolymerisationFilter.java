package top.gunplan.ric.common;

import top.gunplan.netty.GunChannelException;
import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.impl.GunNettyInputFilterChecker;

/**
 * GunRicPolymerisationFilter
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 11:25
 */
public interface GunRicPolymerisationFilter extends GunNettyFilter {
    @Override
    default DealResult doInputFilter(GunNettyInputFilterChecker gunNettyInputFilterChecker) throws GunChannelException {
        return DealResult.NEXT;
    }
}
