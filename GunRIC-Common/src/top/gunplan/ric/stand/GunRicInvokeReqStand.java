package top.gunplan.ric.stand;


/**
 * GunRicInvokeReqStand
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-18 23:37
 */
public interface GunRicInvokeReqStand extends GunRicParamBaseStand {


    Class<?> returnType();

    /**
     * get parameters
     *
     * @return parameters
     */
    Object[] parameters();


    void pushParams(Object[] args);
}
