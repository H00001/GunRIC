package top.gunplan.ric.center;

import top.gunplan.ric.center.record.*;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

/**
 * @author dosdrtt
 */
public class GunRicCenterNewRegisterEvent implements GunRicCommonRealDeal {
    private GunRicCenterRecordManage manage = GunRicCenterStdRecordManage.Instance.getHinstance();

    GunRicCenterNewRegisterEvent() {

    }


    @Override
    public AbstractGunRicProtocol dealDataEvent(AbstractGunRicProtocol protocol) {
        GunRicRegisterProtocol protocol1 = (GunRicRegisterProtocol) protocol;
        GunAddressItem ai = new GunAddressItem(protocol1.getIp(), protocol1.getPort());
        GunRicCdtInterface gg = new GunRicCdtInterface(protocol1);
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
