package top.gunplan.ric.center;

import top.gunplan.ric.center.contest.F;
import top.gunplan.ric.center.manage.check.GunRicCoreHeartTimer;
import top.gunplan.ric.center.manage.impl.GunRicProviderClientImpl;
import top.gunplan.ric.center.record.*;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.stand.GunRicRegisterStand;
import top.gunplan.ric.stand.GunRicRegisterStateStand;


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
            GunRicProviderClientImpl impl = new GunRicProviderClientImpl(ai,interfaceInformation );
            GunRicCoreHeartTimer.providerManage.register(impl);
            manage.doRegex(interfaceInformation, ai);
        } catch (GunRicCenterRecordFailException exp) {
            F.LOG.error(exp);
            o.setCode(RicProtocolCode.FAIL);
            throw (exp);
        }
        return o;
    }
}
