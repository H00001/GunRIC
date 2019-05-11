package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @date
 */
public interface GunRicOutputHelper {
    /**
     * 1557448247
     *
     * @return serial needed space;
     */
    GunBytesUtil.GunWriteByteStream createSpace();
}
