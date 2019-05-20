package top.gunplan.RIC.center;

import top.gunplan.netty.GunException;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;

import top.gunplan.ric.protocol.GunRicRegisterStatusProtocol;
import top.gunplan.ric.protocol.RicProtocolCode;
import top.gunplan.ric.protocol.RicProtocolParamType;
import top.gunplan.ric.protocol.util.PathUtil;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @date 1557152414
 * Concurrent class
 */
public class GunRicCenterStdHandle {
    private GunRicCenterRecordManage manage = new GunRicCenterStdRecordManageImpl();


    private final static String L = "/";
    private final static String D = "_";
    private final static String DT = ".";
    private final static String SFN = "services" + L;


    public GunNetOutputInterface dealDataEvent(GunRicRegisterProtocol pt, InetSocketAddress a) throws GunException {
        final String r = PathUtil.getRes();
        final String mn = pt.gMN();
        final String in = pt.gIN();
        final Class<?>[] t = pt.getTypes();
        final long hh = h(pt.getTypes());
        InetSocketAddress is = new InetSocketAddress(a.getAddress(), pt.getPort());
        File f = new File(r + SFN + in.replace(DT, L));
        boolean exi = f.mkdirs();
        GunRicInterfaceBuffer.GunRicCdtInterface gg = new GunRicInterfaceBuffer.GunRicCdtInterface(hh, t, in, mn);


        f = new File(f.getPath() + L + mn + D + hh);
        GunRicRegisterStatusProtocol o = new GunRicRegisterStatusProtocol(pt.getSerialnumber());
        try {
            manage.addRecord(f, gg, is);
        } catch (Exception exp) {
            exp.printStackTrace();
            o.setCode(RicProtocolCode.FAIL);
            throw new GunException(exp);
        }
        o.setCode(RicProtocolCode.SUCCEED);
        return o;
    }


    private static long h(Class<?>[] paramtypes) {
        long hashh;
        hashh = paramtypes.length;
        int hashl = 0;
        for (Class<?> paramtype : paramtypes) {
            RicProtocolParamType tp = RicProtocolParamType.valuefrom(paramtype);
            hashl += tp.val;
        }
        return (hashh << 32) | hashl;

    }
}
