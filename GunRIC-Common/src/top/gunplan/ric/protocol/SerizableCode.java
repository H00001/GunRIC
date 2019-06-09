package top.gunplan.ric.protocol;

/**
 * @author dosdrtt
 * @date 2019/05/25
 */
public interface SerizableCode {

    /**
     * create instance
     *
     * @return instance
     */
    static SerizableCode newInstance() {
        return new GunRicSerizableCodeImpl();
    }

    /**
     * getSerizNum32
     *
     * @return number 32 bit
     */
    int getSerizNum32();

    /**
     * getSerizNum64
     *
     * @return number 64 bit
     */
    long getSerizNum64();
}
