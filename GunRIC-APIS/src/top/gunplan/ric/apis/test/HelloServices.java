package top.gunplan.ric.apis.test;

import top.gunplan.ric.apis.test.anno.GunUseImpl;

/**
 * HelloServices (Test)
 * #date 2019/04/22
 * @author dosdrtt
 */
@GunUseImpl(impl = "top.gunplan.ric.provider.lib.services.RealSayHello")
public interface HelloServices {
    /**
     * server will say theirs Hello
     * @param msg input message
     * @return message
     */
    String sayHello(String msg);
    /**
     * server will say theirs GoodBye
     * @param msg input message
     * @return message
     */
    String sayGoodBye(String msg);
}
