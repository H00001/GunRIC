package top.gunplan.ric.common.tct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import top.gunplan.ric.protocol.GunAddressItem4;
import top.gunplan.ric.protocol.GunAddressItemInterface;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.util.Arrays;

public final class TRicRespAddressProtocolTest {
    private GunAddressItemInterface item = null;

    @Test
    void doAddressItemTest() {
        GunAddressItem4 tm = new GunAddressItem4();
        int port = 8800;
        String addr = "127.0.0.1";
        byte[] bts = tm.serialize();
        GunAddressItem4 tm1 = new GunAddressItem4();
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
