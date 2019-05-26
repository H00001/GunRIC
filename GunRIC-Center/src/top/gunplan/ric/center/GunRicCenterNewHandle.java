package top.gunplan.ric.center;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;

import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @deprecated 2019/05/26
 * @author dosdrtt
 */
@Deprecated
public class GunRicCenterNewHandle implements GunNettyHandle {
    private final GunRicCenterStdHandle exec = new GunRicCenterStdHandle();

//    {
//        exec.init();
//    }

    @Deprecated
    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface gunNetInputInterface) throws GunException {
        final GunRicCenterDto cdto = (GunRicCenterDto) gunNetInputInterface;
        final List<GunRicRegisterProtocol> lgii = cdto.getObji();
        GunNetOutputInterface[] gol = new GunNetOutputInterface[lgii.size()];

        for (int i = 0; i < lgii.size(); i++) {
            gol[i] = exec.dealDataEvent(lgii.get(i), cdto.getAddress());
        }
        cdto.setObjo(gol);
        return cdto;
    }

    @Deprecated
    @Override
    public GunNetOutputInterface dealConnEvent(SocketChannel socketChannel) throws GunException {
        return null;
    }

    @Override
    @Deprecated
    public void dealCloseEvent() {

    }

    @Override
    @Deprecated
    public void dealExceptionEvent(Exception e) {

    }
}
