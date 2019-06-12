package top.gunplan.ric.common;

import top.gunplan.ric.protocol.GunAddressItem;

import java.util.List;

/**
 * @author dosdrtt
 */
public interface GunRicCommonBuffered<T extends GunRicCommonExeIst> {
    /**
     * push
     *
     * @param key GunRicCommonExeIst
     * @param addresses list GunAddressItem
     */

    void push(T key, List<GunAddressItem> addresses);

    /**
     * get
     *
     * @param key get key
     * @return List<GunAddressItem>
     */
    List<GunAddressItem> get(T key);


    void clear();


}