package top.gunplan.ric.stand;


/**
 * GunRicInvokeReqStand
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-18 23:37
 */
public interface GunRicInvokeReqStand extends GunRicParamBaseStand {

    /**
     * get paramlen
     *
     * @return parameter length
     */
    @Override
    int paramLength();

    /**
     * get parameters
     *
     * @return parameters
     */
    Object[] parameters();


    /**
     * get parameter paramTypes
     *
     * @return paramTypes
     */
    @Override
    Class<?>[] paramTypes();


}
