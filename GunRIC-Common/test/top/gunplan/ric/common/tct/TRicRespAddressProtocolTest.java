package top.gunplan.ric.common.tct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import top.gunplan.ric.protocol.GunAddressItem;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.util.Arrays;

public final class TRicRespAddressProtocolTest {
    GunAddressItem item = null;

    @Test
    void doAddressItemTest() {
        GunAddressItem tm = new GunAddressItem();
        int port = 8800;
        String addr = "127.0.0.1";
        byte[] bts = tm.serialize();
        GunAddressItem tm1 = new GunAddressItem();
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
