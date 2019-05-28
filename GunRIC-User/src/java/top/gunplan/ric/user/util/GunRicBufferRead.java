package top.gunplan.ric.user.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dosdrtt
 */
public final class GunRicBufferRead {
    public static byte[] bufferRead(InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        byte[] b1 = new byte[len];
        System.arraycopy(buffer, 0, b1, 0, len);
        return b1;
    }
}
