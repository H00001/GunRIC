package top.gunplan.services;

import top.gunplan.RPC.APIS.test.HighMath;

public class HighMathImpl implements HighMath {
    @Override
    public int fourierFormat(int [][] a) {
        return a[0][0] +a[0][1];
    }
}
