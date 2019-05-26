package top.gunplan.ric.user;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Stack;

/**
 * @author dosdrtt
 */
public class GunRicUserConncetionFactory {
    public static Socket newSocket(String ip, int port) throws IOException {
        return new Socket(ip, port);
    }

    public static Socket newSocket(InetSocketAddress address) throws IOException {
        return newSocket(address.getHostString(), address.getPort());
    }
}
