package top.gunplan.ric.protocol;

import java.util.Set;

/**
 * GunRicProtocolFactory
 *
 * @author frank albert
 * @version 0.0.0.2
 * #date 2019-06-12 21:36
 */
public class GunRicProtocolFactory {
    static GunRicHelloProtocol newHelloProtocol() {
        return newHelloProtocol();
    }


    public static GunRicRespAddressProtocol newGunRicRespAddressProtocol(Set<GunAddressItemInterface> addresses) {
        return new GunRicRespAddressProtocol(addresses);
    }
}
