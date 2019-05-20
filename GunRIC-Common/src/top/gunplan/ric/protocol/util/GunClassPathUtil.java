package top.gunplan.ric.protocol.util;

import top.gunplan.ric.protocol.GunRicRegisterProtocol;

import java.io.InputStream;

/**
 * @author dosdrtt
 */
public final class GunClassPathUtil {
    public static InputStream getResFileAsStream(String filename) {
        return GunRicRegisterProtocol.class.getClassLoader().getResourceAsStream(filename);
    }

    public static String getBasePath() {
        return GunRicRegisterProtocol.class.getClassLoader().getResource("").getPath();

    }
}
