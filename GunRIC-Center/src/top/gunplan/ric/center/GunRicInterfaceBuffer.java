package top.gunplan.ric.center;

import top.gunplan.ric.center.common.GunRicMethodHash;
import top.gunplan.ric.protocol.AbstractCenterHelperProtocol;
import top.gunplan.ric.protocol.RicProtocolParamType;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;

/**
 * @author dosdrtt
 */
public class GunRicInterfaceBuffer {
    public static HashMap<GunRicCdtInterface, List<InetSocketAddress>> intermapping = new HashMap<>();

    /**
     *
     */
    public static class GunRicCdtInterface {
        private long id;
        private Class<?>[] params;

        public GunRicCdtInterface(AbstractCenterHelperProtocol protocol) {
            this(protocol.getTypes(), protocol.getInterfaceName(), protocol.getMethodName());
        }

        public GunRicCdtInterface(Class<?>[] params, String interFaceName, String methodName) {
            this(interFaceName, methodName);
            this.params = params;
            id = GunRicMethodHash.Instance.getHashInstance().h(interFaceName, methodName, params);
        }

        private GunRicCdtInterface(String interFaceName, String methodName) {
            this.interFaceName = interFaceName;
            this.methodName = methodName;
        }

        public GunRicCdtInterface(RicProtocolParamType[] params, String interFaceName, String methodName) {
            this(interFaceName, methodName);
            this.params = new Class<?>[params.length];
            for (int i = 0; i < params.length; i++) {
                this.params[i] = params[i].clazz;
            }
            id = GunRicMethodHash.Instance.getHashInstance().h(interFaceName, methodName, this.params);
        }

        private final String interFaceName;
        private final String methodName;

        public long getId() {
            return id;
        }

        public Class<?>[] getParams() {
            return params;
        }

        public String getInterFaceName() {
            return interFaceName;
        }

        public String getMethodName() {
            return methodName;
        }

        @Override
        public int hashCode() {
            return (int) id;
        }


        private boolean equals1(GunRicCdtInterface objc) {
            if (interFaceName.equals(objc.interFaceName) &&
                    methodName.equals(objc.methodName) &&
                    objc.params.length == params.length) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i] != objc.params[i]) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }

        private boolean equals0(GunRicCdtInterface objc) {
            return id == objc.id && equals1(objc);
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj) || equals0((GunRicCdtInterface) obj);
        }
    }

}

