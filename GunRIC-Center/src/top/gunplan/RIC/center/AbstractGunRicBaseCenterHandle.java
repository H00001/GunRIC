package top.gunplan.RIC.center;


import top.gunplan.netty.GunException;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.protocol.*;
import top.gunplan.ric.protocol.*;
import top.gunplan.riv.protocol.*;


/**
 * remove this class
 */
public abstract class AbstractGunRicBaseCenterHandle implements GunDubboBaseHandle {
    @Override
    public AbstractGunRicProtocol dealEvent(GunRicInputProtocol protocol) {
        //AbstractGunBaseLogUtil.error("error protocol is GunRicInputProtocol", getClass().getSimpleName());
        throw new GunInvidaProtocolExection(protocol.getClass().getName(), "GunRIC-Center");
    }

    @Override
    public abstract AbstractGunRicProtocol dealEvent(GunRicRegisterProtocol protocol);

    @Override
    public abstract AbstractGunRicProtocol dealEvent(GunRicGetAddressProcotol protocol);

    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface var1) throws GunException {
        if (var1 instanceof GunRicRegisterProtocol) {
            return dealEvent((GunRicRegisterProtocol) var1);
        } else if (var1 instanceof GunRicGetAddressProcotol) {
            return dealEvent((GunRicGetAddressProcotol) var1);
        } else {
            return null;
        }
    }
}
