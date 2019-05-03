package top.gunplan.RCP.center;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.protocol.GunRICRegisterProtocol;
import top.gunplan.protocol.RPCProtoclParamType;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.SocketChannel;

public class GunDubboCenterStdHandle implements GunNettyHandle {
    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface request) throws GunException {
        URL rootpath = this.getClass().getClassLoader().getResource("");
        final GunRICRegisterProtocol protocol = (GunRICRegisterProtocol) request;
        final String path = protocol.getInterfaceName().replace(".", "/");
        String files = rootpath.getPath() + "services/" + path;
        File file = new File(files.replace("%20", " "));
        File md = new File(files.replace("%20", " ") + "/" + protocol.getMethodName());
        try {
            BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(md, true));
            for (int i = 0; i < protocol.getParamlen(); i++) {
                RPCProtoclParamType tp = RPCProtoclParamType.valuefrom(protocol.getTypes()[i]);
                bof.write(tp.val);
            }
            bof.write('\n');
            bof.write(protocol.getPort());
            bof.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean result = file.mkdirs();
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
}
