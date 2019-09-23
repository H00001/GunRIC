package top.gunplan.ric.stand;

/**
 * Serialize
 *
 * @author frank
 * @version 0.0.0.1
 * #date 2019-07-20 08:45
 */
public interface Serialize {

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
