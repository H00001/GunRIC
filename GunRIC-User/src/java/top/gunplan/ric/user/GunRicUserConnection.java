package top.gunplan.ric.user;

import java.io.IOException;

/**
 * GunRicUserConnection
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-06-08 13:34
 */
public interface GunRicUserConnection {
    /**
     * sayHello
     *
     * @return result
     * @throws IOException i/o exception on connect or i/o
     */
    boolean sayHello() throws IOException;
}
