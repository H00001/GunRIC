package top.gunplan.ric.center;

import top.gunplan.ric.center.record.*;
import top.gunplan.ric.protocol.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

/**
 * @author dosdrtt
 */
public class GunRicCenterNewRegisterEvent implements GunRicCommonRealDeal<GunRicRegisterProtocol, GunRicRegisterStatusProtocol> {
    private GunRicCenterRecordManage manage = GunRicCenterStdRecordManage.Instance.getHinstance();

    GunRicCenterNewRegisterEvent() {

    }


    @Override
    public GunRicRegisterStatusProtocol dealDataEvent(GunRicRegisterProtocol protocol) {
        GunAddressItemInterface ai = new GunAddressItem4(protocol.getIp(), protocol.getPort());
        BaseGunRicCdt interfaceInformation = new GunRicCdtImpl(protocol);
        GunRicRegisterStatusProtocol o = new GunRicRegisterStatusProtocol(protocol.getSerialnumber());
        try {
            manage.doRegex(interfaceInformation, ai);
        } catch (GunRicCenterRecordFailException exp) {
            AbstractGunBaseLogUtil.error(exp);
            o.setCode(RicProtocolCode.FAIL);
            throw (exp);
        }
        return o;
    }
}
