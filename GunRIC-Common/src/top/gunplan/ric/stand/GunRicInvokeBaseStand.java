package top.gunplan.ric.stand;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

/**
 * GunRicInvokeBaseStand
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-18 23:43
 */
public interface GunRicInvokeBaseStand extends GunRicBaseStand {
    String methodName();

    String interfaceName();


    default void setINameMName(MethodHandle handle) {

    }

    void setINameMName(Method method);
}
