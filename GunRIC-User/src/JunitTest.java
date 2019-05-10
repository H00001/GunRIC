
import org.junit.jupiter.api.Test;
import top.gunplan.ric.apis.test.CalServicers;
import top.gunplan.RPC.Boot.BootCore;
import top.gunplan.ric.apis.test.LinearAlgebra;
import top.gunplan.ric.apis.test.NatureMath;


import java.io.IOException;

/**
 * RIC Test
 */
class JunitTest {
    @Test
    void dotest() throws IOException {
        /**
         * the framework support data type
         * int 32
         * boolean 8
         * byte 8
         * long 64
         * short 16
         * string ?
         */
        NatureMath servicers = BootCore.IOCObject(NatureMath.class);
//        System.out.printf("3 + 8 = %d\n", servicers.intAdd(3, 8));
//        System.out.printf("8 - 3 = %d\n", servicers.intSub(8, 3));
//        System.out.printf("8 * 3 = %d\n", servicers.multiplication(8, 3));
//        System.out.printf("8 / 3 = %d\n", servicers.division(8, 3));
        int[][] a = {{1, 3, -4}, {-2, 2, 1}, {-3, 4, -2}};
//
//        TestObject o = new TestObject();
//        o.x = 1;
//        o.y = 2;
//        int[] list = {1, 2, 3, 4};
        long ab = 1;
        for (int i = 0; i < 10; i++) {
            ab *= 10;
            System.out.println("样本" + ab + "\t:" + servicers.calNapierianLogarithm(ab));
        }
//        System.out.printf("aaa + zzz = %s\n", servicers.concat("aaa", "zzz"));
//        for (int i = 0; i < 20000; i++) {
//            for (int j = 0; j < 20000; j++) {
//                if (servicers.intAdd(i, j) != i + j) {
//                    System.err.println(i + "+" + j + "!=" + (i + j));
//                } else {
//                    System.out.println(i + "+" + j + "==" + (i + j));
//                }
//            }
//
//        }

    }
}
