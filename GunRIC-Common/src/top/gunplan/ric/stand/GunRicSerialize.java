package top.gunplan.ric.stand;

/**
 * GunRicSerialize
 *
 * @author frank
 * @version 0.0.0.1
 * #date 2019-07-20 08:45
 */
public interface GunRicSerialize {

    /**
     * serializeNumber
     *
     * @return number
     */
    int serializeNumber();

    /**
     * incrementalSeq
     * make sequence number increment
     */
    void incrementalSeq();


    void setSerialnumber(int number);

}
