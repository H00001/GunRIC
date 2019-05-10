package top.gunplan.ric.provider.lib.services;

import top.gunplan.ric.apis.test.NatureMath;

public class NatureMathImpl implements NatureMath {
    @Override
    public String calNapierianLogarithm(long i) {
        return String.valueOf(calNapierianLogarithm0(i));
    }

    private double calNapierianLogarithm0(long i) {
        double var1 = (1 + 1 / (double) i);
        return Math.pow(var1, i);
    }
}
