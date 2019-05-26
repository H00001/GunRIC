package top.gunplan.ric.center;

import top.gunplan.ric.center.record.*;
import top.gunplan.netty.GunException;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;

import top.gunplan.ric.protocol.GunRicRegisterStatusProtocol;
import top.gunplan.ric.protocol.RicProtocolCode;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.net.InetSocketAddress;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @date 1557152414
 * Concurrent class
 */
@Deprecated
class GunRicCenterStdHandle {
    private GunRicCenterStdRecordManage manage = new GunRicCenterStdRecordManage();

    void init() {
        manage.registerFirst(new GunRicCenterPathRecord());
        manage.register(new GunRicCenterInlineBufferRecord());
        manage.register(new GunRicCenterFileRecord());
        manage.register(new GunRicCenterRedisRecord());
    }

    GunNetOutputInterface dealDataEvent(GunRicRegisterProtocol pt, InetSocketAddress a) throws GunException {
        InetSocketAddress is = new InetSocketAddress(a.getAddress(), pt.getPort());
        GunRicInterfaceBuffer.GunRicCdtInterface gg = new GunRicInterfaceBuffer.GunRicCdtInterface(pt);
        GunRicRegisterStatusProtocol o = new GunRicRegisterStatusProtocol(pt.getSerialnumber());
        try {
            manage.doRegex(gg, is);
        } catch (GunRicCenterRecordFailException exp) {
            AbstractGunBaseLogUtil.error(exp);
            o.setCode(RicProtocolCode.FAIL);
            throw (exp);
        }
        o.setCode(RicProtocolCode.SUCCEED);
        return o;
    }


}
