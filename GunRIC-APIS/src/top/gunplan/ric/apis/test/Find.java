package top.gunplan.ric.apis.test;

import top.gunplan.ric.apis.test.anno.GunUseImpl;

/**
 * @author dosdrtt
 */
@GunUseImpl(impl = "top.gunplan.ric.provider.lib.services.FindImpl")
public interface Find {
    /**
     * findByBinaryDivide
     *
     * @param list   find from the list
     * @param target what would find
     * @return element's index ,-1 is can not find
     */
    int findByBinaryDivide(int[] list, int target);

    /**
     * findByBinaryDivide
     *
     * @param list   find from the list
     * @param target what would find
     * @return element's index ,-1 is can not find
     */
    int findByOrder(int[] list, int target);

}
