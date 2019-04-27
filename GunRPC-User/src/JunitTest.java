
import org.junit.jupiter.api.Test;
import top.gunplan.RPC.APIS.test.CalServicers;
import top.gunplan.RPC.Boot.BootCore;


import java.io.IOException;

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
        CalServicers servicers = BootCore.IOCObject(CalServicers.class);
        System.out.printf("3 + 8 = %d\n", servicers.add(3, 8));
        System.out.printf("8 - 3 = %d\n", servicers.sub(8, 3));
        System.out.printf("8 * 3 = %d\n", servicers.multiplication(8, 3));
        System.out.printf("8 / 3 = %d\n", servicers.division(8, 3));
        System.out.printf("aaa + zzz = %s\n", servicers.concat("aaa", "zzz"));
        for (int i = 0; i < 20000; i++) {
            for (int j = 0; j < 20000; j++) {
                if (servicers.add(i, j) != i + j) {
                    System.err.println(i + "+" + j + "!=" + (i + j));
                } else {
                    System.out.println(i + "+" + j + "==" + (i + j));
                }
            }

        }

    }
}
