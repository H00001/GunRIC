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
import java.nio.channels.SocketChannel;

public class GunDubboCenterStdHandle implements GunNettyHandle {
    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface request) throws GunException {
        final String rootpath = DictonaryUtil.getRes();
        final GunRICRegisterProtocol protocol = (GunRICRegisterProtocol) request;
        File file = new File(rootpath + "services/" + protocol.getInterfaceName().replace(".", "/"));
        boolean exi = file.mkdirs();
        File md = new File(file.getPath() + "/" + protocol.getMethodName() + "_" + interHash(protocol.getTypes()));
        try {
            BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(md, true));
            if (md.exists()) {
                bof.write('\n');
                bof.write(protocol.getPort());
            } else {
                for (int i = 0; i < protocol.getParamlen(); i++) {
                    RPCProtoclParamType tp = RPCProtoclParamType.valuefrom(protocol.getTypes()[i]);
                    bof.write(tp.val);
                }
                bof.write('\n');
                bof.write(protocol.getPort());
            }
            bof.close();
        } catch (Exception exp) {
            exp.printStackTrace();
            throw new GunException(exp);
        }
        return null;
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

    private static long interHash(Class<?>[] paramtypes) {
        long hashh;
        hashh = paramtypes.length;
        int hashl = 0;
        for (Class<?> paramtype : paramtypes) {
            hashl += paramtype.hashCode();
        }
        return (hashh << 32) | hashl;

    }
}
