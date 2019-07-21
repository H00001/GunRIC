package top.gunplan.ric.provider;

import java.io.IOException;

/**
 * GunRicPublishManager
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-21 09:06
 */
public interface GunRicPublishManager {

    boolean publishInterface() throws IOException, ReflectiveOperationException;
}
