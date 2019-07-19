package top.gunplan.ric.center.contest;

/**
 * GunRicCenterInformation
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 08:23
 */

public interface GunRicCenterInformation {
    short id();


    String name();


    GunRicCenterInformationAttachObject attach();

    enum GunRicCenterRole {
        /**
         * MASTER   :
         * SLAVE    :
         * OBSERVER :
         * NONE     :
         */

        MASTER, SLAVE, OBSERVER, NONE
    }

    GunRicCenterRole role();


    void changeRole(GunRicCenterRole role);

}
