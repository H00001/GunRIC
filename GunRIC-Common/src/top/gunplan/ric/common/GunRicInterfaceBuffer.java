package top.gunplan.ric.common;


import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.HashMap;
import java.util.List;

/**
 * @author dosdrtt
 */
public class GunRicInterfaceBuffer<T extends GunRicCommonExeIst> implements GunRicCommonBuffered<T> {
    private HashMap<T, List<GunAddressItemInterface>> mapping = new HashMap<>();


    private GunRicInterfaceBuffer() {

    }

    public static <T extends GunRicCommonExeIst> GunRicCommonBuffered<T> newInstance() {
        return new GunRicInterfaceBuffer<>();
    }


    @Override
    public List<GunAddressItemInterface> get(T key) {
        return mapping.get(key);
    }

    @Override
    public void clear() {
        this.mapping.clear();
    }


    @Override
    public void push(T key, List<GunAddressItemInterface> addresses) {
        mapping.put(key, addresses);
    }


}

