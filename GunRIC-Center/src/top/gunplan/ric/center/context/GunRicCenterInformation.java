package top.gunplan.ric.center.context;

/**
 * GunRicCenterInformation
 *
 * @author frank albert
 * @version 0.0.0.2
 * @date 2019-07-19 08:23
 */

public interface GunRicCenterInformation {
    int id();

    String name();

    GunRicCenterInformationAttachObject attach();

    enum GunRicCenterRole {
        /**
         * MASTER   :master
         * SLAVE    :slave
         * OBSERVER :un using
         * NONE     :none
         */

        MASTER, SLAVE, OBSERVER, NONE
    }

    GunRicCenterRole role();


    void changeRole(GunRicCenterRole role);

}
