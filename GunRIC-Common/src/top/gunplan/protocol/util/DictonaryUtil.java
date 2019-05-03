package top.gunplan.protocol.util;

public final class DictonaryUtil {
    public final static String getRes() {
        return DictonaryUtil.class.getClassLoader().getResource("").getPath().replace("%20", " ");
    }
}
