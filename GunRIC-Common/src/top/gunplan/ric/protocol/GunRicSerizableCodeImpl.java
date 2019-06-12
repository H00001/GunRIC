package top.gunplan.ric.protocol;

import java.util.Random;

/**
 * std create serizabel code
 */
class GunRicSerizableCodeImpl implements SerizableCode {
    private Random rand = new Random(System.currentTimeMillis());

    @Override
    public int getSerizNum32() {
        return (int) getSerizNum64();
    }

    @Override
    public long getSerizNum64() {
        return (System.currentTimeMillis()) ^ System.nanoTime() ^ (rand.nextInt(100));
    }
}
