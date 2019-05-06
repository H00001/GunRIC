package top.gunplan.RPC.APIS.test;

import top.gunplan.RPC.APIS.test.anno.GunUseImpl;

import java.io.Serializable;

@GunUseImpl(impl = "top.gunplan.services.HighMathImpl0")
public interface LinearAlgebra {
    CalRes calDet(int[][] a);

    class CalRes implements Serializable {
        boolean isTrueCal;
        int calResult;

        public CalRes(boolean isTrueCal, int calResult) {
            this.isTrueCal = isTrueCal;
            this.calResult = calResult;
        }

        public boolean isTrueCal() {
            return isTrueCal;
        }

        public void setTrueCal(boolean trueCal) {
            isTrueCal = trueCal;
        }

        public int getCalResult() {
            return calResult;
        }

        public void setCalResult(int calResult) {
            this.calResult = calResult;
        }
    }

}
