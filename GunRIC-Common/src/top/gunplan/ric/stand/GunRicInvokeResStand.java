package top.gunplan.ric.stand;


import top.gunplan.ric.protocol.AbstractGunRicExecuteProtocol;

/**
 * GunRicInvokeResStand
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-07-18 23:59
 */
public interface GunRicInvokeResStand extends GunRicInvokeBaseStand {
    /**
     * return result
     *
     * @return ParamHelper
     */
    AbstractGunRicExecuteProtocol.ParamHelper result();


    /**
     * set result
     *
     * @param value result
     */
    void setResult(AbstractGunRicExecuteProtocol.ParamHelper value);

}
