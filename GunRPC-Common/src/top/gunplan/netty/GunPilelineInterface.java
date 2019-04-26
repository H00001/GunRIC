package top.gunplan.netty;


import java.util.List;

/**
 * @author dosdrtt
 * @since 1.0.0.1
 */
public interface GunPilelineInterface {
    /**
     * register the handle in the chain
     * @param handle register handle
     * @return chain style is self
     */
    GunPilelineInterface register(GunHandle handle);

    /**
     *
     * @param filter GunNettyFilter
     * @return continue select
     */
    GunPilelineInterface addFilter(GunNettyFilter filter);

    /**
     *
     * @param handle handle
     * @return this
     */
    GunPilelineInterface setHandle(GunNettyHandle handle);

    /**
     *
     * @param clazz refSetHandle
     * @return this
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    GunPilelineInterface refSetHandle(Class<? extends GunHandle> clazz) throws IllegalAccessException, InstantiationException;

    /**
     * check the pileline model avilable
     * @return check result
     */
    GunPilelineCheckResult check();


    List<GunNettyFilter> getFilters();



    GunNettyHandle getHandel();






}
