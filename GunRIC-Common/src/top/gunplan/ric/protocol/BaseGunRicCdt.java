package top.gunplan.ric.protocol;

import top.gunplan.ric.common.GunRicCommonExeIst;
import top.gunplan.ric.common.GunRicMethodHash;

import java.util.Arrays;

/**
 * @author dosdrtt
 * @version 0.0.1.1
 */
public abstract class BaseGunRicCdt implements GunRicCommonExeIst {


    final String interfaceName;
    final String methodName;
    long id;
    Class<?>[] params;

    BaseGunRicCdt(Class<?>[] params, String interFaceName, String methodName) {
        this(interFaceName, methodName);
        this.params = params;
        id = GunRicMethodHash.Instance.getHashInstance().h(interFaceName, methodName, params);
    }

    public BaseGunRicCdt(RicProtocolParamType[] params, String interFaceName, String methodName) {
        this(interFaceName, methodName);
        this.params = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            this.params[i] = params[i].clazz;
        }
        id = GunRicMethodHash.Instance.getHashInstance().h(interFaceName, methodName, this.params);
    }

    public BaseGunRicCdt(AbstractCenterHelperProtocol help) {
        this(help.paramTypes(), help.interfaceName, help.methodName);
    }

    BaseGunRicCdt(String interFaceName, String methodName) {
        this.interfaceName = interFaceName;
        this.methodName = methodName;
    }


    public long getId() {
        return id;
    }

    public Class<?>[] getParams() {
        return params;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public int hashCode() {
        return GunRicMethodHash.Instance.getHashInstance().h(interfaceName, methodName, params);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BaseGunRicCdt) {
            BaseGunRicCdt cdt = (BaseGunRicCdt) obj;
            if (hashCode() == cdt.hashCode()) {
                return interfaceName.equals(cdt.interfaceName) &&
                        methodName.equals(cdt.methodName) &&
                        Arrays.equals(params, cdt.params);
            }
        }
        return false;

    }
}
