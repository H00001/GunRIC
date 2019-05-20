package top.gunplan.RIC.center;

import java.net.InetSocketAddress;
import java.util.ArrayList;
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
        public GunRicCdtInterface(long id, Class<?>[] params, String interFaceName, String methodName) {
            this.id = id;
            this.params = params;
            this.interFaceName = interFaceName;
            this.methodName = methodName;
        }

        private final long id;
        private final Class<?>[] params;
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

