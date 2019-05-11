package top.gunplan.ric.protocol.util;

/**
 * @author dosdrtt
 */
public final class PathUtil {
    public static String getRes() {
        return PathUtil.class.getClassLoader().getResource("").getPath().replace("%20", " ");
    }
}
