package top.gunplan.RIC.center;

import top.gunplan.netty.GunNettyFilter;
import top.gunplan.netty.anno.GunNetFilterOrder;
import top.gunplan.netty.impl.GunInputFilterChecker;
import top.gunplan.netty.impl.GunOutputFilterChecker;
import top.gunplan.protocol.AbstractGunRPCProtocl;

import java.util.ArrayList;

/**
 * concurrent class
 */
@GunNetFilterOrder(index = 1)
public class GunRICDividePacketFilter implements GunNettyFilter {
    @Override
    public DealResult doInputFilter(GunInputFilterChecker gunInputFilterChecker) throws Exception {
        return null;
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker gunOutputFilterChecker) throws Exception {
        return null;
    }
//    @Override
//    public DealResult doInputFilter(GunInputFilterChecker gunInputFilterChecker) throws Exception {
//        ArrayList<byte[]> bytelist = divide(gunInputFilterChecker.getSrc());
//        gunInputFilterChecker.setObject(bytelist);
//        return null;
//    }
//
//    @Override
//    public DealResult doOutputFilter(GunOutputFilterChecker gunOutputFilterChecker) throws Exception {
//        return null;
//    }
//
//    private ArrayList<byte[]> divide(byte[] src) {
//        ArrayList<byte[]> saved = new ArrayList<>(1);
//        int last = 0;
//        for (int i = 0; i < src.length - 1; i++) {
//            if (src[i] == AbstractGunRPCProtocl.END_FLAGE[0] &&
//                    src[i + 1] == AbstractGunRPCProtocl.END_FLAGE[1]
//            ) {
//                byte[] sbt = new byte[i - last + 1];
//                System.arraycopy(src, last, sbt, 0, i - last + 1);
//                saved.add(sbt);
//            }
//        }
//        return saved;
//    }
}
