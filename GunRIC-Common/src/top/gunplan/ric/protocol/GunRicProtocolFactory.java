package top.gunplan.ric.protocol;

import java.util.List;

/**
 * GunRicProtocolFactory
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-12 21:36
 */
public class GunRicProtocolFactory {
    public static GunRicHelloProtocol newHelloProtocol() {
        return newHelloProtocol();
    }


    public static GunRicRespAddressProtocol newGunRicRespAddressProtocol(List<GunAddressItem> addresses) {
        return new GunRicRespAddressProtocol(addresses);
    }
}
