package top.gunplan.ric.user;


import top.gunplan.ric.common.GunRicCommonBuffered;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.protocol.exp.GunRicProtocolException;
import top.gunplan.utils.AbstractGunBaseLogUtil;


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
            try {
                input.pushParams(args);
            } catch (GunRicProtocolException exp) {
                AbstractGunBaseLogUtil.error(exp);
                return null;
            }
        }
        return connection.send(input).result().obj;
    }


}
