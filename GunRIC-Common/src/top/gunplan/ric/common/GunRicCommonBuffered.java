package top.gunplan.ric.common;

import top.gunplan.ric.protocol.GunAddressItem;

import java.util.List;

/**
 * @author dosdrtt
 */
public interface GunRicCommonBuffered<T extends GunRicCommonExeIst> {
    /**
     * @param key GunRicCommonExeIst
     * @param addresses list GunAddressItem
     */

    void push(T key, List<GunAddressItem> addresses);

    List<GunAddressItem> get(T key);


    void clear();


}