package top.gunplan.ric.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * GunRicBufferUtils
 *
 * @author frank
 */
@FunctionalInterface
public interface GunRicBufferUtils {

    byte[] bufferRead(InputStream in) throws IOException;

    GunRicBufferUtils READER = in -> {
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        byte[] b1 = new byte[len];
        System.arraycopy(buffer, 0, b1, 0, len);
        return b1;
    };


}
