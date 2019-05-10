package top.gunplan.ric.apis.test;

import top.gunplan.ric.apis.test.anno.GunUseImpl;

/**
 * CalServicers (Test)
 * @date 2019/04/22
 * @author dosdrtt
 * this interface must have {@link GunUseImpl} anno
 * @see GunUseImpl
 */
@GunUseImpl(impl = "top.gunplan.ric.provider.lib.services.CalCulServicesImpl")
public interface CalServicers {
    /**
     * add calculator method test
     *
     * @param a left value
     * @param b right value
     * @return left value add right value
     */
    int intAdd(int a, int b);

    /**
     * sub calculator method test
     *
     * @param a left value
     * @param b right value
     * @return left value sub right value
     */
    int intSub(int a, int b);

    /**
     * multiplication calculator method test
     *
     * @param a left value
     * @param b right value
     * @return left value multiplication right value
     */
    int multiplication(int a, int b);

    /**
     * division calculator method test
     *
     * @param a left value
     * @param b left value not == 0
     * @return left value division right value
     */
    int division(int a, int b);


    /**
     * add calculator method test
     *
     * @param a left value
     * @param b right value
     * @return left val add right val
     */
    long longAdd(long a, long b);


    /**
     * add calculator method test
     *
     * @param a left value (byte)
     * @param b right value (byte)
     * @return left value add right value
     */
    int intByteTest(byte a, int b);

    /**
     * add calculator method test
     *
     * @param a left value
     * @param b right value
     * @return right value cat with left value
     */
    String concat(String a, String b);

    /**
     * object calculator method test
     *
     * @param obj input TestObject
     * @return TestObject.x + TestObject.y
     * @see TestObject
     */
    TestObject addObject(TestObject obj, TestObject o);

}
