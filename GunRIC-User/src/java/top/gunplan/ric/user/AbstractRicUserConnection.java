package top.gunplan.ric.user;

import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.ric.protocol.GunRicHelloProtocol;
import top.gunplan.ric.protocol.RicProtocolCode;
import top.gunplan.ric.utils.GunRicBufferUtils;


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
            //todo
            socket = GunRicUserConnectionFactory.newSocket("127.0.0.1-8855");
        } catch (IOException e) {
            GunNettyContext.logger.error(e);
        }
    }

    @Override
    public boolean sayHello() throws IOException {
        GunRicHelloProtocol protocol = new GunRicHelloProtocol(true);
        socket.getOutputStream().write(protocol.serialize());
        protocol.unSerialize(GunRicBufferUtils.READER.bufferRead(socket.getInputStream()));
        return protocol.code() == RicProtocolCode.HELLO_RES;
    }

}
