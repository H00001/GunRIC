package top.gunplan.RIC.center;

/**
 * this class is used to record manage
 *
 * @author dosdrtt
 * @date 1558736561
 */
public interface GunRicCenterRecordManage {
    /**
     * check it is first record
     *
     * @param g record object
     * @return is or not
     */
    boolean isFirst(GunRicInterfaceBuffer.GunRicCdtInterface g);
}
