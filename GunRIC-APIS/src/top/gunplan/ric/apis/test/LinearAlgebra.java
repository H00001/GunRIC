package top.gunplan.ric.apis.test;

import top.gunplan.ric.apis.test.anno.GunUseImpl;

import java.io.Serializable;

/**
 * LinearAlgebra calculator class
 * @author dosdrtt
 * @date 1557231535
 */
@GunUseImpl(impl = "top.gunplan.ric.provider.lib.services.LinearAlgebraImpl")
public interface LinearAlgebra {
    /**
     * calculator the det value
     *
     * @param a    det
     * @param size det's size
     * @return calres
     */
    CalRes calDet(int[][] a, int size);


    class CalRes implements Serializable {
        private static final long serialVersionUID = -8759739552340900011L;
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
