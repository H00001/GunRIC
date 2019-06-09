package top.gunplan.ric.center.record;


import top.gunplan.ric.center.GunRicCdtInterface;
import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.GunAddressItem;

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
    public void firstAdd(GunRicCdtInterface g, GunAddressItem address) {
        // jedis.lpush(g.getInterFaceName() + ":" + g.getMethodName(), address.getAddress() + "-" + address.getPort());
    }

    @Override
    public void nextAdd(GunRicCdtInterface g, GunAddressItem address) {
        // jedis.lpush(g.getInterFaceName() + ":" + g.getMethodName(), address.getAddress() + "-" + address.getPort());
    }

    @Override
    List<GunAddressItem> getAddressBase(GunRicCdtInterface g) {
        return null;
    }
}
