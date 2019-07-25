package top.gunplan.ric.protocol;


import top.gunplan.ric.stand.GunRicParamBaseStand;

/**
 * GunRicServerInformationImpl
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-13 09:30
 */
public class GunRicServerInformationImpl extends BaseGunRicServerInformation {

    private GunRicServerInformationImpl(Class<?>[] params, String interFaceName, String methodName) {
        super(params, interFaceName, methodName);
    }

    public GunRicServerInformationImpl(RicProtocolParamType[] params, String interFaceName, String methodName) {
        super(params, interFaceName, methodName);
    }

    public GunRicServerInformationImpl(GunRicParamBaseStand help) {
        this(help.paramTypes(), help.interfaceName(), help.methodName());
    }

    private GunRicServerInformationImpl(String interFaceName, String methodName) {
        super(interFaceName, methodName);
    }




    @Override
    public int hashCode() {
        return (int) id;
    }


    private boolean equals1(BaseGunRicServerInformation objc) {
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

    private boolean equals0(BaseGunRicServerInformation objc) {
        return id == objc.getId() && equals1(objc);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || equals0((BaseGunRicServerInformation) obj);
    }
}

