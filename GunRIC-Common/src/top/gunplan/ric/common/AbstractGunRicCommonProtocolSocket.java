package top.gunplan.ric.common;

import top.gunplan.ric.protocol.AbstractGunRicProtocol;
import top.gunplan.ric.protocol.GunRicTypeDividePacketManage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * AbstractGunRicCommonProtocolSocket
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-14 11:52
 */
public abstract class AbstractGunRicCommonProtocolSocket extends AbstractGunRicCommonSocket {

    AbstractGunRicCommonProtocolSocket(String addr, int port) throws IOException {
        super(addr, port);
    }

    public void sendProtocol(AbstractGunRicProtocol protocol) throws IOException {
        super.sendTcpData(protocol.serialize());
    }

    public <U extends AbstractGunRicProtocol> U receiveProtocol(Class<U> uClass) throws ReflectiveOperationException, IOException {
        U object = uClass.getDeclaredConstructor().newInstance();
        object.unSerialize(super.receiveTcpData());
        return object;
    }

    public AbstractGunRicProtocol receiveProtocol() throws ReflectiveOperationException, IOException {
        AbstractGunRicProtocol protocol = GunRicTypeDividePacketManage.findPackage(super.receiveTcpData());
        protocol.unSerialize(super.receiveTcpData());
        return protocol;
    }
}
