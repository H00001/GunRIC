package top.gunplan.RPC.Boot.test;

import org.junit.jupiter.api.Test;
import top.gunplan.RPC.Boot.BootCore;

interface HelloServices {
    String sayHello();


}

class sdd {
    @Test
    void dotest() {
        HelloServices hs = (HelloServices) BootCore.IOCObject(HelloServices.class);
        System.out.println((String)hs.sayHello());
    }
}
