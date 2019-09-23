package top.gunplan.ric.stand;


/**
 * GunRicInvokeReqStand
 *
 * @author frank
 * @version 0.0.0.2
 * #date 2019-07-18 23:37
 */
public interface GunRicInvokeReqStand extends GunRicParamBaseStand {

    /**
     * return type
     *
     * @return type
     */

    Class<?> returnType();

    /**
     * get parameters
     *
     * @return parameters
     */
    Object[] parameters();


    /**
     * push params to protocol
     *
     * @param args params
     */
    void pushParams(Object[] args);
}
