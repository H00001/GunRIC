package top.gunplan.ric.stand;

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
