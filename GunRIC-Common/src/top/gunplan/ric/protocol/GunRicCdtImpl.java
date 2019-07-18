package top.gunplan.ric.protocol;


import top.gunplan.ric.stand.GunRicParamBaseStand;

/**
 * GunRicCdtImpl
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-13 09:30
 */
public class GunRicCdtImpl extends BaseGunRicCdt {

    private GunRicCdtImpl(Class<?>[] params, String interFaceName, String methodName) {
        super(params, interFaceName, methodName);
    }

    public GunRicCdtImpl(RicProtocolParamType[] params, String interFaceName, String methodName) {
        super(params, interFaceName, methodName);
    }

    public GunRicCdtImpl(GunRicParamBaseStand help) {
        this(help.paramTypes(), help.interfaceName(), help.methodName());
    }

    private GunRicCdtImpl(String interFaceName, String methodName) {
        super(interFaceName, methodName);
    }

//    public GunRicCdtImpl(AbstractCenterHelperProtocol protocol) {
//
//    }


    @Override
    public int hashCode() {
        return (int) id;
    }


    private boolean equals1(BaseGunRicCdt objc) {
        if (interfaceName.equals(objc.getInterfaceName()) &&
                methodName.equals(objc.getMethodName()) &&
                objc.getParams().length == params.length) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] != objc.getParams()[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean equals0(BaseGunRicCdt objc) {
        return id == objc.getId() && equals1(objc);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || equals0((BaseGunRicCdt) obj);
    }
}

