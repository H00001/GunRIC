package top.gunplan.ric.center.record;


import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.manage.check.GunRicCoreHeartTimer;
import top.gunplan.ric.center.manage.impl.GunRicProviderClientImpl;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.List;

/**
 * use redis to record
 *
 * @author dosdrtt
 * @date 1558736830
 * @see GunRicCenterRecord
 */
public class GunRicCenterRedisRecord extends AbstractGunRicProxyRecord {


    public GunRicCenterRedisRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
//        jedis = new Jedis("", 0);
//        jedis.auth();
    }

    @Override
    public void firstAdd(BaseGunRicCdt g, GunAddressItemInterface address) {

        // jedis.lpush(g.interfaceName() + ":" + g.methodName(), address.getAddress() + "-" + address.portNumber());
    }

    @Override
    public void nextAdd(BaseGunRicCdt g, GunAddressItemInterface address) {

        // jedis.lpush(g.interfaceName() + ":" + g.methodName(), address.getAddress() + "-" + address.portNumber());
    }

    @Override
    List<GunAddressItemInterface> getAddressBase(BaseGunRicCdt g) {
        return null;
    }
}
