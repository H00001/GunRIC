package top.gunplan.ric.center.record;


import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.protocol.BaseGunRicServerInformation;
import top.gunplan.ric.protocol.GunAddressItemInterface;

import java.util.Set;

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
    public void firstAdd(BaseGunRicServerInformation g, GunAddressItemInterface address) {

        // jedis.lpush(g.interfaceName() + ":" + g.methodName(), address.getAddress() + "-" + address.portNumber());
    }

    @Override
    public void nextAdd(BaseGunRicServerInformation g, GunAddressItemInterface address) {

        // jedis.lpush(g.interfaceName() + ":" + g.methodName(), address.getAddress() + "-" + address.portNumber());
    }

    @Override
    public void remove(GunAddressItemInterface address) {

    }

    @Override
    Set<GunAddressItemInterface> getAddressBase(BaseGunRicServerInformation g) {
        return null;
    }
}
