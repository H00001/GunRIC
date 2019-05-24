package top.gunplan.RIC.center.record;

import top.gunplan.RIC.center.GunRicCenterRecord;
import top.gunplan.RIC.center.GunRicInterfaceBuffer;

import java.net.InetSocketAddress;

/**
 * use redis to record
 *
 * @author dosdrtt
 * @date 1558736830
 * @see GunRicCenterRecord
 */
public class GunRicCenterRedisRecord implements GunRicCenterRecord {
    @Override
    public void firstAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) {

    }

    @Override
    public void nextAdd(GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) {

    }
}
