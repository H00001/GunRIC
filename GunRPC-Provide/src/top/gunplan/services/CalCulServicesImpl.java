package top.gunplan.services;

import top.gunplan.RPC.APIS.test.CalServicers;

/**
 * @author dosdrtt
 */
public class CalCulServicesImpl implements CalServicers {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }
}
