package top.gunplan.ric.user.test;

import org.junit.jupiter.api.Test;
import top.gunplan.ric.protocol.GunRicGetAddressProtocol;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;
import top.gunplan.ric.user.GunRicUserProperty;
import top.gunplan.ric.user.GunRicUserPropertyManageImpl;
import top.gunplan.ric.user.UserBoot;

import java.io.IOException;
import java.lang.annotation.Target;
import java.net.Socket;

public class ServicesTest {

    @Test
    public void getServerAddress() throws IOException {
        Socket so = new Socket("127.0.0.1", 8855);
        GunRicGetAddressProtocol poctol = new GunRicGetAddressProtocol();
        poctol.setInterfaceName("top.gunplan.ric.apis.test.Find");
        poctol.setMethodName("findByBinaryDivide");
        poctol.setParamcount(2);
        poctol.pushParamType(int[].class);
        poctol.pushParamType(int.class);
        poctol.setSerialnumber(1);
        so.getOutputStream().write(poctol.serialize());
        byte[] buffer = new byte[1024];

        int len = so.getInputStream().read(buffer);
        byte[] b1 = new byte[len];
        System.arraycopy(buffer, 0, b1, 0, len);
        GunRicRespAddressProtocol protocol = new GunRicRespAddressProtocol();
        System.out.println("cc");
        protocol.unSerialize(b1);

        System.out.println("cc");
    }
}
