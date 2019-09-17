package top.gunplan.ric.common;

import top.gunplan.ric.protocol.GunRicTypeDividePacketManage;
import top.gunplan.ric.stand.GunRicBaseStand;

import java.io.IOException;

/**
 * AbstractGunRicCommonProtocolSocket
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-06-14 11:52
 */
public abstract class AbstractGunRicCommonProtocolSocket extends AbstractGunRicCommonSocket {

    AbstractGunRicCommonProtocolSocket(String addr, int port) throws IOException {
        super(addr, port);
    }

    public void sendProtocol(GunRicBaseStand protocol) throws IOException {
        super.sendTcpData(protocol.serialize());
    }

    public <U extends GunRicBaseStand> U receiveProtocol(Class<U> uClass) throws ReflectiveOperationException, IOException {
        U object = uClass.getDeclaredConstructor().newInstance();
        object.unSerialize(super.receiveTcpData());
        return object;
    }

    public GunRicBaseStand receiveProtocol() throws ReflectiveOperationException, IOException {
        GunRicBaseStand protocol = GunRicTypeDividePacketManage.findPackage(super.receiveTcpData());
        protocol.unSerialize(super.receiveTcpData());
        return protocol;
    }
}
