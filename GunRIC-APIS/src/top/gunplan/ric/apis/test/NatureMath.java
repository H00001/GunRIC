package top.gunplan.ric.apis.test;


import top.gunplan.ric.apis.test.anno.GunUseImpl;

@GunUseImpl(impl = "top.gunplan.ric.provider.lib.services.NatureMathImpl")
public interface NatureMath {
    String calNapierianLogarithm(long i);
}
