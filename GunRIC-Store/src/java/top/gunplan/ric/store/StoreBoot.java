package top.gunplan.ric.store;

import top.gunplan.netty.GunBootServerBase;
import top.gunplan.utils.AbstractGunBaseLogUtil;

/**
 * @author dosdrtt
 * @see top.gunplan.netty.GunBootServerBase
 */
public class StoreBoot implements GunBootServerBase {
    public static void main(String[] args) {
        try {
            new StoreBoot().sync();
        } catch (Exception e) {
            AbstractGunBaseLogUtil.error(e);
        }
    }

    @Override
    public int sync() throws Exception {
        return 0;
    }
}
