package top.gunplan.ric.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * GunRicExecutors
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-07-21 09:21
 */
public final class GunRicExecutors {
    public static ExecutorService newSignalExector() {
        return createExecutorService(1, 1, 10, null);
    }

    public static ExecutorService newValueBufferExector(int value, int buffer) {
        return createExecutorService(value, value, buffer, null);
    }

    private static ExecutorService createExecutorService(int base, int max, int buffer, String name) {
        return new ThreadPoolExecutor(base, max, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(buffer), new GunRicThreadFactory(name));
    }
}
