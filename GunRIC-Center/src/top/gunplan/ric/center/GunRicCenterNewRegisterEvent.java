package top.gunplan.ric.center;

import top.gunplan.ric.center.record.*;
import top.gunplan.ric.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.net.InetSocketAddress;

/**
 * @author dosdrtt
 */
public class GunRicCenterNewRegisterEvent implements GunRicCommonRealDeal {
    private GunRicCenterRecordManage manage = new GunRicCenterStdRecordManage();

    GunRicCenterNewRegisterEvent() {
        this.init();
    }

    private void init() {
        manage.registerFirst(new GunRicCenterPathRecord());
        manage.register(new GunRicCenterInlineBufferRecord());
        manage.register(new GunRicCenterFileRecord());
        manage.register(new GunRicCenterRedisRecord());
    }

    @Override
    public AbstractGunRicProtocol dealDataEvent(AbstractGunRicProtocol protocol) {
        GunRicRegisterProtocol protocol1 = (GunRicRegisterProtocol) protocol;
        InetSocketAddress is = new InetSocketAddress(protocol1.getIp(), ((GunRicRegisterProtocol) protocol).getPort());
        GunRicInterfaceBuffer.GunRicCdtInterface gg = new GunRicInterfaceBuffer.GunRicCdtInterface((AbstractCenterHelperProtocol) protocol);
        GunRicRegisterStatusProtocol o = new GunRicRegisterStatusProtocol(protocol.getSerialnumber());
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
