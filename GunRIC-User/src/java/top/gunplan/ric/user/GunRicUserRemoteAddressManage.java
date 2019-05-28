package top.gunplan.ric.user;

import top.gunplan.ric.protocol.GunAddressItem;

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
    public static Map<String, List<GunAddressItem>> mmap = new HashMap<>();

    public static void clearMmap() {
        mmap.clear();
    }

    void push(String iName, List<GunAddressItem> address) {
        mmap.put(iName, address);
    }

    void push(String iName, GunAddressItem address) {
        List<GunAddressItem> items = mmap.get(iName);
        if (items != null) {
            items.add(address);
        } else {
            ArrayList<GunAddressItem> ads = new ArrayList<>();
            ads.add(address);
            push(iName, ads);
        }

    }

}
