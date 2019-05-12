package top.gunplan.ric.user;

import top.gunplan.netty.impl.propertys.GunProperty;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.net.InetSocketAddress;

/**
 * @author dosdrtt
 * @nonconcurrent
 * @time 1557658871
 * @see top.gunplan.netty.impl.propertys.GunProperty
 */
public class GunRicUserProperty implements GunProperty {
    private static final String DIV_FLAG = ",";
    private String centerAddress;
    private InetSocketAddress[] address;

    public InetSocketAddress[] getAddress() {
        return address;
    }

    @Override
    public boolean isAvailable() {
        try {
            String[] servers;

            if (centerAddress.contains(DIV_FLAG)) {
                servers = centerAddress.split(DIV_FLAG);
            } else {
                servers = new String[]{centerAddress};
            }
            address = new InetSocketAddress[servers.length];
            for (int i = 0; i < servers.length; i++) {
                address[i] = new InetSocketAddress(servers[i].split("-")[0], Integer.parseInt(servers[i].split("-")[1]));
            }
        } catch (Exception e) {
            AbstractGunBaseLogUtil.error(e);
            return false;
        }
        return true;
    }

}
