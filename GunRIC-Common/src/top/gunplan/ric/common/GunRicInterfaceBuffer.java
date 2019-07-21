package top.gunplan.ric.common;


import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dosdrtt
 */
public class GunRicInterfaceBuffer<T extends GunRicCommonExeIst> implements GunRicCommonBuffered<T> {
    private final Map<T, Set<GunAddressItemInterface>> mapping = new ConcurrentHashMap<>(2);


    private GunRicInterfaceBuffer() {

    }

    public static <T extends GunRicCommonExeIst> GunRicCommonBuffered<T> newInstance() {
        return new GunRicInterfaceBuffer<>();
    }


    @Override
    public Set<GunAddressItemInterface> get(T key) {
        return mapping.get(key);
    }

    @Override
    public void clear() {
        this.mapping.clear();
    }

    @Override
    public void remove(GunAddressItemInterface address) {
        mapping.forEach((k, v) -> v.removeIf(t -> t.equals(address)));
    }


    @Override
    public void push(T key, Set<GunAddressItemInterface> addresses) {
        mapping.put(key, addresses);
    }


}

