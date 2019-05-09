package top.gunplan.RIC.center;

import top.gunplan.netty.GunException;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.protocol.GunRicRegisterProtocol;

import top.gunplan.protocol.GunRicRegisterStatusProtocol;
import top.gunplan.protocol.RicProtocolCode;
import top.gunplan.protocol.RicProtocolParamType;
import top.gunplan.protocol.util.PathUtil;


import java.io.BufferedOutputStream;
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
public class GunDubboCenterStdHandle {

    private final static String L = "/";
    private final static String D = "_";
    private final static String DT = ".";
    private final static String SFN = "services" + L;


    public GunNetOutputInterface dealDataEvent(GunNetInputInterface request, InetSocketAddress a) throws GunException {
        final String r = PathUtil.getRes();
        final GunRicRegisterProtocol pt = ((GunRicRegisterProtocol) request);
        final String mn = pt.gMN();
        final String in = pt.gIN();
        final Class<?>[] t = pt.getTypes();
        final long hh = h(pt.getTypes());
        InetSocketAddress is = new InetSocketAddress(a.getAddress(), pt.getPort());
        File f = new File(r + SFN + in.replace(DT, L));
        boolean exi = f.mkdirs();
        f = new File(f.getPath() + L + mn + D + hh);
        GunRicRegisterStatusProtocol o = new GunRicRegisterStatusProtocol();
        o.setSerialnumber(pt.getSerialnumber());
        try {
            BufferedOutputStream bf;
            if (f.exists()) {
                bf = new BufferedOutputStream(new FileOutputStream(f, true));
                //      GunRICInterfaceBuffer.intermapping.get(new GunRICInterfaceBuffer.GunRICInterface(hh, t, in, mn)).add(is);
                //
                wP(pt, a.getHostString(), bf);
            } else {
                bf = new BufferedOutputStream(new FileOutputStream(f, true));
                for (Class<?> aClass : t) {
                    RicProtocolParamType tp = RicProtocolParamType.valuefrom(aClass);
                    bf.write(tp.val);
                }
                //fa(is, mn, in, t, hh);
                wP(pt, a.getHostString(), bf);
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            o.setCode(RicProtocolCode.FAIL);
            throw new GunException(exp);
        }
        o.setCode(RicProtocolCode.SUCCEED);
        return o;
    }

    private void fa(InetSocketAddress is, String mn, String in, Class<?>[] t, long hh) {
        ArrayList<InetSocketAddress> al = new ArrayList<>(1);
        GunRICInterfaceBuffer.GunRICInterface gg = new GunRICInterfaceBuffer.GunRICInterface(hh, t, in, mn);
        al.add(is);
        GunRICInterfaceBuffer.intermapping.put(gg, al);
    }

    private void wP(GunRicRegisterProtocol protocol, String addr, BufferedOutputStream bof) throws IOException {
        bof.write('\n');
        bof.write((protocol.getPort() + D + addr).getBytes());
        bof.close();
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
