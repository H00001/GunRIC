package top.gunplan.ric.common;

import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Set;

/**
 * @author dosdrtt
 */
public interface GunRicCommonBuffered<T extends GunRicCommonExeIst> {
    /**
     * push
     *
     * @param key       GunRicCommonExeIst
     * @param addresses list GunAddressItem4
     */

    void push(T key, Set<GunAddressItemInterface> addresses);

    /**
     * get
     *
     * @param key get key
     * @return List<GunAddressItem4>
     */
    Set<GunAddressItemInterface> get(T key);


    /**
     * clear the buffer
     */
    void clear();


    void remove(GunAddressItemInterface address);

}