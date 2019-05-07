
import org.junit.jupiter.api.Test;
import top.gunplan.RPC.APIS.test.LinearAlgebra;
import top.gunplan.RPC.APIS.test.TestObject;
import top.gunplan.RPC.Boot.BootCore;


import java.io.IOException;

/**
 *
 */
public class JunitTest {
    @Test
    void dotest() throws IOException {
        /**
         * the framework support data type
         * int 64
         * boolean 8
         * byte 8
         * string ?
         */
        LinearAlgebra servicers = BootCore.IOCObject(LinearAlgebra.class);
//        System.out.printf("3 + 8 = %d\n", servicers.intAdd(3, 8));
//        System.out.printf("8 - 3 = %d\n", servicers.intSub(8, 3));
//        System.out.printf("8 * 3 = %d\n", servicers.multiplication(8, 3));
//        System.out.printf("8 / 3 = %d\n", servicers.division(8, 3));
        int[][] a = {{1, 2, -4}, {-2, 2, 1}, {-3, 4, -2}};
        TestObject o = new TestObject();
        o.x = 1;
        o.y = 2;
        int[] list = {1, 2, 3, 4};
        System.out.println(servicers.calDet(a, 3).getCalResult());
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
