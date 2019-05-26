package top.gunplan.ric.center;

import top.gunplan.netty.impl.GunNettyDefaultObserveImpl;
import top.gunplan.netty.impl.propertys.GunProperty;
import top.gunplan.ric.center.record.*;
import top.gunplan.utils.AbstractGunBaseLogUtil;

/**
 * GunRicCenterObserve
 *
 * @author dosdrtt
 */
public class GunRicCenterObserve extends GunNettyDefaultObserveImpl {
    private GunRicCenterRecordManage manage = GunRicCenterStdRecordManage.Instance.getHinstance();

    @Override
    public boolean onBooting(GunProperty gunProperty) {
        super.onBooting(gunProperty);
        manage.registerFirst(new GunRicCenterPathRecord(null));
        AbstractGunRicProxyRecord r2 = new GunRicCenterFileRecord(new GunRicCenterRedisRecord(new GunRicCenterInlineBufferRecord(null)));
        manage.registerLoop(r2);
        try {
            return GunRicRegisterManage.loadRegister();
        } catch (Exception e) {
            AbstractGunBaseLogUtil.error(e);
            return false;
        }
    }

    @Override
    public void onBooted(GunProperty property) {
        super.onBooted(property);
    }
}
