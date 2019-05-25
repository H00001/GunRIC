package top.gunplan.ric.user.test;

import org.junit.jupiter.api.Test;

import top.gunplan.ric.apis.test.ListQuestion;
import top.gunplan.ric.user.UserBoot;


import java.io.IOException;
import java.util.Arrays;

/**
 * RIC Test
 */
class JunitTest {
    @Test
    void dotest() throws IOException, InterruptedException {
        /**
         * the framework support data type
         * int 32
         * boolean 8
         * byte 8
         * long 64
         * short 16
         * string ?
         */
//        CalServices services = UserBoot.iocObject(CalServices.class);
//        System.out.printf("3 + 8 = %d\n", services.intAdd(3, 8));
//        System.out.printf("8 - 3 = %d\n", services.intSub(8, 3));
//        System.out.printf("8 * 3 = %d\n", services.multiplication(8, 3));
//        System.out.printf("8 / 3 = %d\n", services.division(8, 3));

        int[][] a = {{1, 3, -4}, {-2, 2, 1}, {-3, 4, -2}};
        int[] lss = {0, 1, 2, 0, 4, 5, 0};
        int[] lisg = {1, 2, 3, 4, 5, 6, 7, 9, 10, 20, 80, 90, 100, 200};
        int[] x = {1, 0, 2};
        int[] y = {0, 1, -3};
//
        ListQuestion services1 = UserBoot.iocObject(ListQuestion.class);
        System.out.println(Arrays.toString(services1.removeVal(lisg, 3).getList()));
//        TestObject o = new TestObject();
//        o.x = 1;
//        o.y = 2;
//        int[] list = {1, 2, 3, 4};
//        long ab = 1;
//        for (int i = 0; i < 100; i++) {
//            ab *= 10;
//            Thread.sleep(2000);
//            System.out.println("样本" + ab + "\t:" + servicers.calNapierianLogarithm(ab));
//        }
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
