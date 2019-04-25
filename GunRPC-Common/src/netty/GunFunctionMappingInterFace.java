package netty;

import java.io.IOException;

/**
 * @author dosdrtt
 */
@FunctionalInterface
public interface GunFunctionMappingInterFace<F, T> {
    /**
     * @param from from
     * @return i do not know
     * @throws IOException IOException
     */
    T readBytes(F from) throws IOException;
}
