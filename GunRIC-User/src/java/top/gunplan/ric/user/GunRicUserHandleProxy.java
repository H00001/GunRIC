package top.gunplan.ric.user;


import top.gunplan.ric.common.GunRicCommonBuffered;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.user.util.GunRicBufferRead;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @since 0.0.0.1
 */

public class GunRicUserHandleProxy extends AbstractGunRicUserHandleProxy {

    private GunRicUserUsedConnection connection = new GunRicUserUsedConnection();


    @Override
    public Object sendMessage(Method method, Object[] args) throws IOException {
        connection.getAddressItem(method);
        GunRicInputProtocol input = new GunRicInputProtocol();
        input.setInameMname(method);
        if (args != null) {
            input.pushParams(args);
        }
        return connection.send(input).getReturnValue().obj;
    }


}
