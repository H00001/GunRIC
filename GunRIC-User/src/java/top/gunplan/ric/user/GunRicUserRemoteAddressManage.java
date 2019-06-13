package top.gunplan.ric.user;

import top.gunplan.ric.protocol.GunAddressItem4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GunRicUserRemoteAddressManage
 *
 * @author dosdrtt
 */
public final class GunRicUserRemoteAddressManage {
    private static Map<String, List<GunAddressItem4>> mmap = new HashMap<>();

    public static void clearMmap() {
        mmap.clear();
    }

    void push(String iName, List<GunAddressItem4> address) {
        mmap.put(iName, address);
    }

    void push(String iName, GunAddressItem4 address) {
        List<GunAddressItem4> items = mmap.get(iName);
        if (items != null) {
            items.add(address);
        } else {
            ArrayList<GunAddressItem4> ads = new ArrayList<>();
            ads.add(address);
            push(iName, ads);
        }

    }

}
