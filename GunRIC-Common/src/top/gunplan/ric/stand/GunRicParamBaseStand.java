package top.gunplan.ric.stand;
/**
 * GunRicParamBaseStand
 *
 * @author frank albert
 * #date 2019-07-19 08:20
 *
 * @version 0.0.0.1
 */
public interface GunRicParamBaseStand extends GunRicInvokeBaseStand {

    /**
     * get paramlen
     *
     * @return parameter length
     */
    int paramLength();


    /**
     * get parameter paramTypes
     *
     * @return paramTypes
     */
    Class<?>[] paramTypes();
}
