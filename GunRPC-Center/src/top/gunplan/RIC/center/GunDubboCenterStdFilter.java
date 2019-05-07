package top.gunplan.RIC.center;


import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
import top.gunplan.protocol.AbstractGunRPCProtocl;
import top.gunplan.protocol.GunRPCDividePacketManage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @date 1557231535
 */
@GunNetFilterOrder(index = 1)
public class GunDubboCenterStdFilter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunInputFilterChecker filterDto) {
        InetSocketAddress address;
        try {
            address = (InetSocketAddress) ((SocketChannel) filterDto.getKey().channel()).getRemoteAddress();
        } catch (IOException e) {
            return DealResult.NOTDEALALLNEXT;
        }
        AbstractGunRPCProtocl protocol = GunRPCDividePacketManage.findPackage(filterDto.getSrc());
        if (protocol != null) {

            GunRicCenterDto dto = new GunRicCenterDto(address, protocol);
            filterDto.setObject(dto);
            return DealResult.NEXT;
        }
        return DealResult.NOTDEALALLNEXT;

    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker filterDto) throws Exception {
        return null;
    }
}
