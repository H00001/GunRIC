package top.gunplan.netty.impl.buffer;

import top.gunplan.netty.GunBufferManage;
import sun.misc.Unsafe;

public class GunUnsafePulledBufferManage implements GunBufferManage {
    public byte[] malloc(int size, int len) throws Exception {
        final Unsafe unsafe = newUnsafe();
        long address = unsafe.allocateMemory(size * len);
//        unsafe.putby
        return null;
    }

    @Override
    public void addEvent() {

    }

    @Override
    public void descEvent() {

    }

    @Override
    public GunPulledLinkedData malloc(int len) {
        return null;
    }
}
