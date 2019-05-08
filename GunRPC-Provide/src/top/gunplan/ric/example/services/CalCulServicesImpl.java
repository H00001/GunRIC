package top.gunplan.ric.example.services;

import top.gunplan.ric.apis.test.CalServicers;
import top.gunplan.ric.apis.test.TestObject;



/**
 * @author dosdrtt
 */
public class CalCulServicesImpl implements CalServicers {
    @Override
    public int intAdd(int a, int b) {
        return a + b;
    }

    @Override
    public int intSub(int a, int b) {
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
    public long longAdd(long a, long b) {
        return a+b;
    }

    @Override
    public int intByteTest(byte a, int b) {
        return a + b;
    }

    @Override
    public String concat(String a, String b) {
        return a + b;
    }

    @Override
    public int mAdd(int[] list) {
        int num = 0;
        for (int value : list) {
            num += value;
        }
        return num;
    }

    @Override
    public int addObject(TestObject obj) {
        TestObject objc =  obj;
        return objc.x + objc.y;
    }
}
