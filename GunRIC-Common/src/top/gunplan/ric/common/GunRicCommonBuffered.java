package top.gunplan.ric.common;

import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.List;

/**
 * @author dosdrtt
 */
public interface GunRicCommonBuffered<T extends GunRicCommonExeIst> {
    /**
     * push
     *
     * @param key GunRicCommonExeIst
     * @param addresses list GunAddressItem4
     */

    void push(T key, List<GunAddressItemInterface> addresses);

    /**
     * get
     *
     * @param key get key
     * @return List<GunAddressItem4>
     */
    List<GunAddressItemInterface> get(T key);


    /**
     * clear the buffer
     */
    void clear();


}