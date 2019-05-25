package top.gunplan.ric.center;


import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
import top.gunplan.ric.protocol.GunRicRegisterProtocol;
import top.gunplan.ric.protocol.GunRicTypeDividePacketManage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dosdrtt
 * @date 1557231535
 */
@Deprecated
@GunNetFilterOrder(index = 2)
public class GunRicCenterStdFilter implements GunNettyFilter {
    @Override
    @Deprecated
    public DealResult doInputFilter(GunInputFilterChecker filterDto) {
        InetSocketAddress address;
        try {
            address = (InetSocketAddress) ((SocketChannel) filterDto.getKey().channel()).getRemoteAddress();
        } catch (IOException e) {
            return DealResult.NOTDEALALLNEXT;
        }
        final GunRicDividePacketFilter.GunDividePacketDto dto = (GunRicDividePacketFilter.GunDividePacketDto) filterDto.getObject();
        if (dto.size != 0) {
            List<GunRicRegisterProtocol> gnii = new ArrayList<>(1);
            for (int i = 0; i < dto.size; i++) {
                GunRicRegisterProtocol protocol = (GunRicRegisterProtocol) GunRicTypeDividePacketManage.findPackage(dto.getPackets().get(i));
                if (protocol.unSerialize(dto.getPackets().get(i))) {
                    gnii.add(protocol);
                }
            }
            GunRicCenterDto rdto = new GunRicCenterDto(address, gnii);
            //   GunRicCenterDto dto = new GunRicCenterDto(address, protocol);

            filterDto.setObject(rdto);
            return DealResult.NEXT;
        }
        return DealResult.NOTDEALALLNEXT;

    }

    @Deprecated
    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker filterDto) throws Exception {
        return null;
    }
}
