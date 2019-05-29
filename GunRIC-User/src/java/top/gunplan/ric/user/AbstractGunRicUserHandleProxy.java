package top.gunplan.ric.user;

import top.gunplan.ric.protocol.GunAddressItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

/**
 * @author dosdrtt
 */
public abstract class AbstractGunRicUserHandleProxy implements InvocationHandler {
    GunRicUserProperty property = GunRicUserPropertyManageImpl.getProperty();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<GunAddressItem> addressItems = getAddress(method);
        Socket ss = GunRicUserConnectionFactory.newSocket(addressItems.get(0).getAddress(), addressItems.get(0).getPort());
        sendMessage(method, args, ss.getOutputStream());
        return receiveMessage(ss.getInputStream(), 0);
    }

    abstract List<GunAddressItem> getAddress(Method method) throws IOException;


    abstract Object receiveMessage(InputStream inputStream, int i) throws IOException;

    abstract void sendMessage(Method method, Object[] args, OutputStream outputStream) throws IOException;

}
