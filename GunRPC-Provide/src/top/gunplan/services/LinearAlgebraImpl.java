package top.gunplan.services;

import top.gunplan.RPC.APIS.test.LinearAlgebra;

/**
 * LinearAlgebra function lib
 */

public class LinearAlgebraImpl implements LinearAlgebra {
    @Override
    public CalRes calDet(int[][] a,int size) {
        if (a.length != a[0].length) {
            return new CalRes(false, 0);
        } else {
            float[][] d = fp(a);
            int P = a.length;
            for (int i = 0; i < P; i++) {
                for (int j = i + 1; j < P; j++) {
                    rmListZero(d, i, j, P);
                }
            }

            return new CalRes(true, oppositeanglesDet(d));
        }
    }

    private float[][] fp(int[][] a) {
        float[][] fp = new float[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                fp[i][j] = a[i][j];
            }
        }
        return fp;
    }

    private void rmListZero(float[][] a, int i, int j, int p) {
        float ptr = a[i][i];
        float c = a[j][i] / ptr;
        for (int k = 0; k < p; k++) {
            a[j][k] = (a[j][k] - a[i][k] * c);
        }
    }

    private int oppositeanglesDet(float[][] a) {
        float val = 1;
        for (int i = 0; i < a.length; i++) {
            val *= a[i][i];
        }
        return (int) val;
    }

}
