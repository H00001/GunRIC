package top.gunplan.ric.protocol;

import java.util.Random;

/**
 * std create serial code
 */
class GunRicSerialCodeImpl implements SerizableCode {
    private Random rand = new Random(System.currentTimeMillis());

    @Override
    public short getSerialNum16() {
        short v = (short) getSerialNum32();
        if (v < 0) {
            v = (short) -v;
        }
        return v;
    }

    @Override
    public int getSerialNum32() {
        return (int) getSerialNum64();
    }

    @Override
    public long getSerialNum64() {
        return ((System.currentTimeMillis()) ^ System.nanoTime() ^ (rand.nextInt(100))) >> 1;
    }
}
