package top.gunplan.ric.center.manage.impl;

import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.ric.center.manage.AbstractGunRicClientManager;
import top.gunplan.ric.center.manage.GunProviderAliveCheckResult;
import top.gunplan.ric.center.manage.GunRicProviderClient;
import top.gunplan.ric.center.manage.GunRicProviderManage;
import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Set;
import java.util.stream.Stream;

import static top.gunplan.ric.center.manage.GunRICStateRecorder.ConnectionState.LOSTCONECTION;

/**
 * GunRicProviderManageImpl
 *
 * @author frank albert
 * @version 0.0.0.4
 * @date 2019-07-19 20:50
 */
public class GunRicProviderManageImpl extends AbstractGunRicClientManager<GunRicProviderClient> implements GunRicProviderManage {

    @Override
    public void register(GunAddressItemInterface user, BaseGunRicServerInformation cdt) {
        boolean have = false;
        for (var v : clients) {
            if (v.addressInformation().equals(user)) {
                v.addCdt(cdt);
                have = true;
            }
        }
        if (!have) {
            clients.add(new GunRicProviderClientImpl(user, cdt));
        }
    }


    @Override
    public Set<GunRicProviderClient> removeUnuseProvider() {
        return null;
    }


    @Override
    public GunProviderAliveCheckResult aliveCheck() {
        GunNettyContext.logger.setTAG(GunRicProviderManageImpl.class).info("alive count is:" + clients.size());
        try {
            clients.parallelStream().forEach(GunRicProviderClient::doCheck);
            Stream<GunRicProviderClient> clientStream = clients.parallelStream().filter(who -> who.state() == LOSTCONECTION);
            inforToRecorder(clientStream);
            clients.removeIf(who -> who.state() == LOSTCONECTION);

        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }


}
