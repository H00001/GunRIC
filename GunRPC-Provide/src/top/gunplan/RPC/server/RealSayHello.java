package top.gunplan.RPC.server;

import top.gunplan.RPC.APIS.test.HelloServices;

public class RealSayHello implements HelloServices {
    @Override
    public String sayHello(String msg) {
        return "hi sssss";
    }

    @Override
    public String sayGoodBay(String msg) {
        return "good bye " + msg;
    }
}
