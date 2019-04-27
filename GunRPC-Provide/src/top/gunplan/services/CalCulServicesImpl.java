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

    @Override
    public int multiplication(int a, int b) {
        return a * b;
    }

    @Override
    public int division(int a, int b) {
        return b != 0 ? a / b : 0;
    }

    @Override
    public boolean getResult(boolean b) {
        return b;
    }

    @Override
    public int intByteTest(byte a, int b) {
        return a+b;
    }
}
