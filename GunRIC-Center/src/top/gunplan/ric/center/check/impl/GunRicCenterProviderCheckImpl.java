package top.gunplan.ric.center.check.impl;

import top.gunplan.ric.center.check.GunRicCenterProviderCheck;
import top.gunplan.ric.common.*;

import top.gunplan.ric.protocol.GunRicHelloProtocol;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;


/**
 * GunRicCenterProviderCheckImpl
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-14 09:41
 */
public class GunRicCenterProviderCheckImpl implements GunRicCenterProviderCheck {
    @Override
    public int doCheck() {
        AbstractGunRicCommonProtocolSocket ss = null;
        GunRicHelloProtocol pt = new GunRicHelloProtocol(true);
        try {
            ss = GunRicUserConnectionFactoryImpl.newSocket("127.0.0.1", 8822);
            short seq = (short) pt.getSerialnumber();
            ss.sendProtocol(pt);
            pt = ss.receiveProtocol(pt.getClass());
            if (seq == pt.getSerialnumber()) {
                AbstractGunBaseLogUtil.debug("receive")
                ;
            } else {
                AbstractGunBaseLogUtil.error(seq + "seq fail" + pt.getSerialnumber() + pt.getCode());
            }

        } catch (IOException e) {
            AbstractGunBaseLogUtil.error("connect fail");
        } catch (ReflectiveOperationException e) {
            AbstractGunBaseLogUtil.error("protocol error");
        }


        return 0;
    }
}
