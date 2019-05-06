package top.gunplan.services;

import top.gunplan.RPC.APIS.test.LinearAlgebra;

public class HighMathImpl implements LinearAlgebra {
    @Override
    public CalRes calDet(int[][] a) {
        if (a.length != a[0].length) {
            return new CalRes(false, 0);
        } else {
            return  new CalRes(false, 2270);
        }
    }
}
