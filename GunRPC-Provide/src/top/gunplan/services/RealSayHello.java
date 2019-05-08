package top.gunplan.services;

import top.gunplan.RPC.APIS.test.HelloServices;

/**
 * @author dosdrtt
 */
public class RealSayHello implements HelloServices {
    @Override
    public String sayHello(String msg) {
        return "hello "+msg;
    }

    @Override
    public String sayGoodBay(String msg) {
        return "good bye " + msg;
    }
}
