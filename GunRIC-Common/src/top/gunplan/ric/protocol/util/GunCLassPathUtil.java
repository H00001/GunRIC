package top.gunplan.ric.protocol.util;

import top.gunplan.ric.protocol.GunRicRegisterProtocol;

import java.io.InputStream;


public final class GunCLassPathUtil {
    public static InputStream getResFileAsStream(String filename) {
        return GunRicRegisterProtocol.class.getClassLoader().getResourceAsStream(filename);
    }
}
