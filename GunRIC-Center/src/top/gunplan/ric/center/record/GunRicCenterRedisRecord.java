package top.gunplan.ric.center.record;

import redis.clients.jedis.Jedis;
import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.GunAddressItem;
import top.gunplan.ric.protocol.GunRicRespAddressProtocol;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * use redis to record
 *
 * @author dosdrtt
 * @date 1558736830
 * @see GunRicCenterRecord
 */
public class GunRicCenterRedisRecord extends AbstractGunRicProxyRecord {
    private Jedis jedis;

    public GunRicCenterRedisRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
//        jedis = new Jedis("", 0);
//        jedis.auth();
    }

    @Override
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunAddressItem address) {
        // jedis.lpush(g.getInterFaceName() + ":" + g.getMethodName(), address.getAddress() + "-" + address.getPort());
    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, GunAddressItem address) {
        // jedis.lpush(g.getInterFaceName() + ":" + g.getMethodName(), address.getAddress() + "-" + address.getPort());
    }

    @Override
    List<GunAddressItem> getAddressBase(GunRicInterfaceBuffer.GunRicCdtInterface g) {
        return null;
    }
}
