package top.gunplan.ric.user;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author dosdrtt
 */
public abstract class AbstractGunRicUserHandleProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return sendMessage(method, args);
    }

    /**
     * sendMessage
     *
     * @param method method
     * @param args   paramteers
     * @return result
     * @throws IOException i/o or connect exception
     */
    abstract Object sendMessage(Method method, Object[] args) throws IOException;

}
