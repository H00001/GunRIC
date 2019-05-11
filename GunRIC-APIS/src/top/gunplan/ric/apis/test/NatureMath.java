package top.gunplan.ric.apis.test;


import top.gunplan.ric.apis.test.anno.GunUseImpl;

/**
 * @author dosdrtt
 */
@GunUseImpl(impl = "top.gunplan.ric.provider.lib.services.NatureMathImpl")
public interface NatureMath {
    /**
     * @param i
     * @return
     */
    String calNapierianLogarithm(long i);
}
