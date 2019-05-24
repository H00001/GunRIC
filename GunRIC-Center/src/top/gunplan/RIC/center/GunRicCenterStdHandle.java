package top.gunplan.RIC.center;

import top.gunplan.RIC.center.record.GunRicCenterFileRecord;
import top.gunplan.RIC.center.record.GunRicCenterInlineBufferRecord;
import top.gunplan.RIC.center.record.GunRicCenterPathRecord;
import top.gunplan.RIC.center.record.GunRicCenterRedisRecord;
import top.gunplan.netty.GunException;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;

import top.gunplan.ric.protocol.GunRicRegisterStatusProtocol;
import top.gunplan.ric.protocol.RicProtocolCode;
import java.net.InetSocketAddress;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @date 1557152414
 * Concurrent class
 */
class GunRicCenterStdHandle {
    private GunRicCenterStdRecordManage manage = new GunRicCenterStdRecordManage();


    private final static String L = "/";
    private final static String D = "_";


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
        } catch (Exception exp) {
            exp.printStackTrace();
            o.setCode(RicProtocolCode.FAIL);
            throw new GunException(exp);
        }
        o.setCode(RicProtocolCode.SUCCEED);
        return o;
    }


}
