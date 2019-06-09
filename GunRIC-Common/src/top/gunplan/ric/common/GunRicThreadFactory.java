package top.gunplan.ric.common;

import java.util.concurrent.ThreadFactory;

/**
 * GunRicThreadFactory
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-06-08 13:25
 */


public class GunRicThreadFactory implements ThreadFactory {
    private String baseName = "";

    public GunRicThreadFactory(String baseName) {
        this.baseName = baseName;
    }

    public GunRicThreadFactory() {

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        t.setPriority(5);
        t.setName(this.getClass().getSimpleName() + "-" + baseName + "-thread-pool-");
        return t;
    }
}
