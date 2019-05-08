package top.gunplan.ric.apis.test;

import top.gunplan.ric.apis.test.anno.GunUseImpl;

@GunUseImpl(impl = "top.gunplan.ric.example.services.CalCulServicesImpl")
public interface CalServicers {
    int intAdd(int a, int b);

    int intSub(int a, int b);

    int multiplication(int a, int b);

    int division(int a, int b);

    boolean getResult(boolean b);

    long longAdd(long a, long b);

    int intByteTest(byte a, int b);

    String concat(String a, String b);

    int mAdd(int[] list);

    int addObject(TestObject obj);

}
