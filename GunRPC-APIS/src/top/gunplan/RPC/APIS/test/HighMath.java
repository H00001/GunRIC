package top.gunplan.RPC.APIS.test;

import top.gunplan.RPC.APIS.test.anno.GunUseImpl;

@GunUseImpl(impl = "top.gunplan.services.HighMathImpl")
public interface HighMath {
    int fourierFormat(int[][] a);

}
