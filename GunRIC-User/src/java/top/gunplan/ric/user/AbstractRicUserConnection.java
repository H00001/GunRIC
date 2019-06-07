package top.gunplan.ric.user;

import top.gunplan.ric.protocol.GunRicHelloProtocol;
import top.gunplan.ric.protocol.RicProtocolCode;
import top.gunplan.ric.user.util.GunRicBufferRead;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * AbstractRicUserConnection
 *
 * @author dosdrtt
 */
public abstract class AbstractRicUserConnection implements GunRicUserConnection {

    Socket socket;

    public AbstractRicUserConnection() {
        try {
            socket = GunRicUserConnectionFactory.newSocket(GunRicUserPropertyManageImpl.getProperty().getAddress()[0]);
        } catch (IOException e) {
            AbstractGunBaseLogUtil.error(e);
            return;
        }
    }

    @Override
    public boolean sayHello() throws IOException {
        GunRicHelloProtocol protocol = new GunRicHelloProtocol(true);
        socket.getOutputStream().write(protocol.serialize());
        protocol.unSerialize(GunRicBufferRead.bufferRead(socket.getInputStream()));
        return protocol.getCode() == RicProtocolCode.HELLO_RES;
    }

}
