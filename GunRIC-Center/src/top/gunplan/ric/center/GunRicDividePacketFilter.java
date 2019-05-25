package top.gunplan.ric.center;

import top.gunplan.netty.GunException;
import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.ric.protocol.AbstractGunRicProtocol;

import java.util.ArrayList;

/**
 * concurrent class
 *
 * @author dosdrtt
 * @date 2019/04/27
 */
@Deprecated
@GunNetFilterOrder(index = 1)
public class GunRicDividePacketFilter implements GunNettyFilter {
    @Override
    @Deprecated
    public DealResult doInputFilter(GunInputFilterChecker gunInputFilterChecker) throws Exception {
        final ArrayList<byte[]> pb = divide(gunInputFilterChecker.getSrc());
        GunDividePacketDto dto = new GunDividePacketDto(pb, pb.size());
        gunInputFilterChecker.setObject(dto);
        return DealResult.NEXT;
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker gunOutputFilterChecker) throws Exception {
        return null;
    }

    public static class GunDividePacketDto implements GunNetInputInterface {
        final ArrayList<byte[]> packets;
        final int size;

        GunDividePacketDto(ArrayList<byte[]> packets, int size) {
            this.packets = packets;
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        ArrayList<byte[]> getPackets() {
            return packets;
        }

        @Override
        public boolean unSerialize(byte[] bytes) {
            throw new GunException("list can not unserilize");
        }
    }

    private ArrayList<byte[]> divide(byte[] src) {
        ArrayList<byte[]> saved = new ArrayList<>(1);
        int last = 0;
        for (int i = 0; i < src.length - 1; i++) {
            if (src[i] == AbstractGunRicProtocol.END_FLAG[0] &&
                    src[i + 1] == AbstractGunRicProtocol.END_FLAG[1]
            ) {
                byte[] sbt = new byte[i - last + 2];
                System.arraycopy(src, last, sbt, 0, i - last + 2);
                last = i + 2;
                saved.add(sbt);
            }
        }
        return saved;
    }
}
