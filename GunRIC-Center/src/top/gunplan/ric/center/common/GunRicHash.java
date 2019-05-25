package top.gunplan.ric.center.common;

/**
 * GunRicHash a hash interface
 *
 * @author dosdrtt
 */
public interface GunRicHash {
    int h(final String interfaceName, final String methodName, Class<?>[] params);

}
