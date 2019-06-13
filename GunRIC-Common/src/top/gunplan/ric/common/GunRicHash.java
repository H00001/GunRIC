package top.gunplan.ric.common;

/**
 * GunRicHash a hash interface
 *
 * @author dosdrtt
 */
public interface GunRicHash {

    /**
     * h get hash code
     *
     * @param interfaceName hash 0
     * @param methodName    hash 1
     * @param params        hash 2
     * @return hash code
     */
    int h(final String interfaceName, final String methodName, Class<?>[] params);

}
