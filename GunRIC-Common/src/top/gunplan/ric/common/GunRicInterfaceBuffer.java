package top.gunplan.ric.common;


import top.gunplan.ric.protocol.GunAddressItem;

import java.util.HashMap;
import java.util.List;

/**
 * @author dosdrtt
 */
public class GunRicInterfaceBuffer<T extends GunRicCommonExeIst> implements GunRicCommonBuffered<T> {
    private HashMap<T, List<GunAddressItem>> intermapping = new HashMap<>();


    private GunRicInterfaceBuffer() {

    }

    public static <T extends GunRicCommonExeIst> GunRicCommonBuffered<T> newInstance() {
        return new GunRicInterfaceBuffer<>();
    }


    @Override
    public List<GunAddressItem> get(T key) {
        return intermapping.get(key);
    }

    @Override
    public void clear() {
        this.intermapping.clear();
    }


    @Override
    public void push(T key, List<GunAddressItem> addresses) {
        intermapping.put(key, addresses);
    }


}

