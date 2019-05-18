package top.gunplan.ric.common.tct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.util.Arrays;

public final class TRicRespAddressProtocolTest {
    GunRicRespAddressProtocol.AddressItem item = null;

    @Test
    void doAddressItemTest() {
        GunRicRespAddressProtocol.AddressItem tm = new GunRicRespAddressProtocol.AddressItem();
        int port = 8800;
        String addr = "127.0.0.1";
        tm.setAddress(addr);
        tm.setPort(port);
        byte[] bts = tm.serialize();
        GunRicRespAddressProtocol.AddressItem tm1 = new GunRicRespAddressProtocol.AddressItem();
        tm1.unSerialize(bts);
        Assertions.assertEquals(addr, tm1.getAddress());
        Assertions.assertEquals(port, tm1.getPort());
        this.item = tm1;
    }

    @Test
    void doAddressRespTest() {
        doAddressItemTest();
        GunRicRespAddressProtocol pt = new GunRicRespAddressProtocol();
        pt.pushAddress(item);
        pt.pushAddress(item);

        System.out.println(Arrays.toString(pt.serialize()));
    }
}
