package top.gunplan.ric.center.check;

/**
 * GunRicCenterProviderCheck
 *
 * @author frank albert
 * @version 0.0.0.2
 * @date 2019-06-14 09:30
 */
public interface GunRicCenterProviderCheck {

    /**
     * doCheck
     * <p>
     * do point health check
     *
     * @return check result
     */
    int doCheck();
}
