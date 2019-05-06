package top.gunplan.RIC.center;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.protocol.GunRICRegisterProtocol;

import top.gunplan.protocol.RPCProtoclParamType;
import top.gunplan.protocol.util.DictonaryUtil;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class GunDubboCenterStdHandle implements GunNettyHandle {
    private final static String P = "services/";
    private final static String L = "/";
    private final static String D = "_";
    private final static String SFN = "services" + L;


    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface request) throws GunException {
        final String r = DictonaryUtil.getRes();
        final GunRICRegisterProtocol pt = (GunRICRegisterProtocol) ((GunRICCenterDto) request).getObj();
        final InetSocketAddress a = ((GunRICCenterDto) request).getAddress();
        final String mn = pt.gMN();
        final String in = pt.gIN();
        final Class<?>[] t = pt.getTypes();
        final long hh = h(pt.getTypes());
        InetSocketAddress is = new InetSocketAddress(a.getAddress(), pt.getPort());
        File f = new File(r + SFN + in.replace(P, L));
        boolean exi = f.mkdirs();
        f = new File(f.getPath() + L + mn + D + hh);
        try {
            BufferedOutputStream bf;
            if (f.exists()) {
                bf = new BufferedOutputStream(new FileOutputStream(f, true));
                fa(is, mn, in, t, hh);
                wP(pt, a.getHostString(), bf);
            } else {
                bf = new BufferedOutputStream(new FileOutputStream(f, true));
                for (int i = 0; i < pt.getParamlen(); i++) {
                    RPCProtoclParamType tp = RPCProtoclParamType.valuefrom(t[i]);
                    GunRICInterfaceBuffer.intermapping.get(new GunRICInterfaceBuffer.GunRICInterface(hh, t, in, mn)).add(is);
                    bf.write(tp.val);
                }
                wP(pt, a.getHostString(), bf);
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            throw new GunException(exp);
        }
        return null;
    }

    private void fa(InetSocketAddress is, String mn, String in, Class<?>[] t, long hh) {
        ArrayList<InetSocketAddress> al = new ArrayList<>(1);
        GunRICInterfaceBuffer.GunRICInterface gg =new GunRICInterfaceBuffer.GunRICInterface(hh, t, in, mn);
        al.add(is);
        GunRICInterfaceBuffer.intermapping.put(gg, al);
    }

    private void wP(GunRICRegisterProtocol protocol, String addr, BufferedOutputStream bof) throws IOException {
        bof.write('\n');
        bof.write((protocol.getPort() + D + addr).getBytes());
        bof.close();
    }


    @Override
    public GunNetOutputInterface dealConnEvent(SocketChannel request) throws GunException {
        return null;
    }

    @Override
    public void dealCloseEvent() {

    }

    @Override
    public void dealExceptionEvent(Exception exp) {

    }

    private static long h(Class<?>[] paramtypes) {
        long hashh;
        hashh = paramtypes.length;
        int hashl = 0;
        for (Class<?> paramtype : paramtypes) {
            RPCProtoclParamType tp = RPCProtoclParamType.valuefrom(paramtype);
            hashl += tp.val;
        }
        return (hashh << 32) | hashl;

    }
}
