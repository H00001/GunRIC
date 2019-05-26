package top.gunplan.ric.center;

import top.gunplan.ric.center.record.*;
import top.gunplan.ric.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.net.InetSocketAddress;

/**
 * @author dosdrtt
 */
public class GunRicCenterNewRegisterEvent implements GunRicCommonRealDeal {
    private GunRicCenterRecordManage manage = GunRicCenterStdRecordManage.Instance.getHinstance();

    GunRicCenterNewRegisterEvent() {
        this.init();
    }

    private void init() {
        manage.registerFirst(new GunRicCenterPathRecord(null));
        AbstractGunRicProxyRecord r2 = new GunRicCenterFileRecord(new GunRicCenterRedisRecord(new GunRicCenterInlineBufferRecord(null)));
        manage.registerLoop(r2);
    }

    @Override
    public AbstractGunRicProtocol dealDataEvent(AbstractGunRicProtocol protocol) {
        GunRicRegisterProtocol protocol1 = (GunRicRegisterProtocol) protocol;
        GunRicRespAddressProtocol.AddressItem ai = new GunRicRespAddressProtocol.AddressItem(protocol1.getIp(), protocol1.getPort());
        GunRicInterfaceBuffer.GunRicCdtInterface gg = new GunRicInterfaceBuffer.GunRicCdtInterface(protocol1);
        GunRicRegisterStatusProtocol o = new GunRicRegisterStatusProtocol(protocol.getSerialnumber());
        try {
            manage.doRegex(gg, ai);
        } catch (GunRicCenterRecordFailException exp) {
            AbstractGunBaseLogUtil.error(exp);
            o.setCode(RicProtocolCode.FAIL);
            throw (exp);
        }
        o.setCode(RicProtocolCode.SUCCEED);
        return o;
    }
}
