package top.gunplan.ric.center.record;

import top.gunplan.ric.center.GunRicCenterRecord;
import top.gunplan.ric.center.GunRicInterfaceBuffer;

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
    public GunRicCenterRedisRecord(AbstractGunRicProxyRecord lastRecord) {
        super(lastRecord);
    }

    @Override
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) {

    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) {

    }

    @Override
    List<InetSocketAddress> getAddressBase(GunRicInterfaceBuffer.GunRicCdtInterface g) {
        return null;
    }
}
