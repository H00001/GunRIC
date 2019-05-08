package top.gunplan.ric.example.services;

import top.gunplan.ric.apis.test.HelloServices;

/**
 * @author dosdrtt
 */
public class RealSayHello implements HelloServices {
    @Override
    public String sayHello(String msg) {
        return "hello "+msg;
    }

    @Override
    public String sayGoodBye(String msg) {
        return "good bye " + msg;
    }
}
