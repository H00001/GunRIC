package top.gunplan.ric.user;


import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.ric.protocol.GunRicInputProtocol;
import top.gunplan.ric.protocol.exp.GunRicProtocolException;

import java.io.IOException;
import java.lang.reflect.Method;

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
                GunNettyContext.logger.error(exp);
                return null;
            }
        }
        return connection.send(input).result().obj;
    }


}
