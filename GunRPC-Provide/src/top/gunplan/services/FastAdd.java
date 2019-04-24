package top.gunplan.services;

import top.gunplan.RPC.APIS.test.CalServicers;

public class FastAdd implements CalServicers {
    @Override
    public int add(int a, int b) {
        return a^b;
    }
}
