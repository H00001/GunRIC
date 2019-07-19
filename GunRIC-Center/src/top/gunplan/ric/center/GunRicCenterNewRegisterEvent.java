package top.gunplan.ric.center;

import top.gunplan.ric.center.record.*;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.stand.GunRicRegisterStand;
import top.gunplan.ric.stand.GunRicRegisterStateStand;
import top.gunplan.utils.AbstractGunBaseLogUtil;

/**
 * @author dosdrtt
 */
public class GunRicCenterNewRegisterEvent implements GunRicCommonRealDeal<GunRicRegisterStand, GunRicRegisterStateStand> {
    private GunRicCenterRecordManage manage = GunRicCenterStdRecordManage.Instance.getHinstance();

    GunRicCenterNewRegisterEvent() {

    }


    @Override
    public GunRicRegisterStateStand dealDataEvent(GunRicRegisterStand protocol) {
        GunAddressItemInterface ai = new GunAddressItem4(protocol.ipAddress(), protocol.portNumber());
        BaseGunRicCdt interfaceInformation = new GunRicCdtImpl(protocol);
        GunRicRegisterStateStand o = new GunRicRegisterStatusProtocol(protocol.serialNumber());
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
