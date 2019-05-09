package top.gunplan.RIC.center;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyHandle;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;

import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @author dosdrtt
 */
public class GunDubboCenterNewHandle implements GunNettyHandle {
    @Override
    public GunNetOutputInterface dealDataEvent(GunNetInputInterface gunNetInputInterface) throws GunException {
        final GunRicCenterDto cdto = (GunRicCenterDto) gunNetInputInterface;
        final List<GunNetInputInterface> lgii = cdto.getObji();
        GunNetOutputInterface[] gol = new GunNetOutputInterface[lgii.size()];
        GunDubboCenterStdHandle exec = new GunDubboCenterStdHandle();
        for (int i = 0; i < lgii.size(); i++) {
            gol[i] = exec.dealDataEvent(lgii.get(i),cdto.getAddress());
        }
        cdto.setObjo(gol);
        return cdto;
    }

    @Override
    public GunNetOutputInterface dealConnEvent(SocketChannel socketChannel) throws GunException {
        return null;
    }

    @Override
    public void dealCloseEvent() {

    }

    @Override
    public void dealExceptionEvent(Exception e) {

    }
}
