package top.gunplan.ric.provider.lib.services;

import top.gunplan.ric.apis.test.LinearAlgebra;

/**
 * LinearAlgebra function lib
 *
 * @author dosdrtt
 */
public class LinearAlgebraImpl implements LinearAlgebra {
//    static {
//        //todo antive
//        System.loadLibrary("LinearAlgebraLib");
//    }


    public native int calDet();

    /**
     * o(n^3)
     * client c
     *
     * @param a det
     * @param s det's size
     * @return calu resu
     */
    @Override
    public CalRes calDet(int[][] a, int s) {
        if (c(a, s)) {
            float[][] d = fp(a, s);
            for (int i = 0; i < s; i++) {
                for (int j = i + 1; j < s; j++) {
                    ro(d, i, j, s);
                }
            }
            return new CalRes(true, ot(d));
        } else {
            return new CalRes(false, -2011230);
        }
    }

    @Override
    public int[] verticalVector(int[] a, int[] b) {
        int[] sv = new int[3];
        sv[0] = a[1] * b[2] - b[1] * a[2];
        sv[1] = b[0] * a[2] - a[0] * b[2];
        sv[2] = a[0] * b[1] - a[1] * b[0];
        return sv;
    }

    private boolean c(int[][] a, int size) {
        if (a.length != size) {
            return false;
        }
        for (int[] l : a) {
            if (l.length != size) {
                return false;
            }
        }
        return true;
    }

    private float[][] fp(int[][] a, int l) {
        float[][] fp = new float[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                fp[i][j] = a[i][j];
            }
        }
        return fp;
    }

    private void ro(float[][] a, int i, int j, int p) {
        float ptr = a[i][i];
        float c = a[j][i] / ptr;
        for (int k = 0; k < p; k++) {
            a[j][k] = (a[j][k] - a[i][k] * c);
        }
    }

    private int ot(float[][] a) {
        float val = 1;
        for (int i = 0; i < a.length; i++) {
            val *= a[i][i];
        }
        return (int) val;
    }
}