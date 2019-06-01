package top.gunplan.ric.user;

import top.gunplan.ric.protocol.GunRicHelloProtocol;
import top.gunplan.ric.protocol.RicProtocolCode;
import top.gunplan.ric.user.util.GunRicBufferRead;

import java.io.IOException;

/**
 * AbstractRicUserConnection
 *
 * @author dosdrtt
 */
public class AbstractRicUserConnection implements GunRicUserConnection {

    GunRicUserSocket socket;

    public AbstractRicUserConnection() {
        // socket = GunRicUserConnectionFactory.newSocket();
    }

    @Override
    public boolean sayHello() throws IOException {
        GunRicHelloProtocol protocol = new GunRicHelloProtocol(true);
        socket.getOutputStream().write(protocol.serialize());
        protocol.unSerialize(GunRicBufferRead.bufferRead(socket.getInputStream()));
        return protocol.getCode() == RicProtocolCode.HELLO_RES;
    }


}
