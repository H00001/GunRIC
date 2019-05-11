package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 */
public interface GunDubboNxInput {
    /**
     * unSerialize
     *
     * @param util GunWriteByteStream
     * @return boolean
     */
    boolean unSerialize(GunBytesUtil.GunReadByteStream util);
}
