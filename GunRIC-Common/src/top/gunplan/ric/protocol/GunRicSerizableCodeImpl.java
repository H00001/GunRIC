package top.gunplan.ric.protocol;

/**
 * std create serizabel code
 */
class GunRicSerizableCodeImpl implements SerizableCode {

    @Override
    public int getSerizNum32() {
        return (int) getSerizNum64();
    }

    @Override
    public long getSerizNum64() {
        return (System.currentTimeMillis() >>> 8) ^ (int) (Math.random() * 100);
    }
}
