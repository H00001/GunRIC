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
    AbstractGunRicExecuteProtocol.ParamHelper result();


    void setResult(AbstractGunRicExecuteProtocol.ParamHelper value);

}
