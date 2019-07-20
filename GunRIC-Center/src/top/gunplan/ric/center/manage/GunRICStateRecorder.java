package top.gunplan.ric.center.manage;

/**
 * GunRICStateRecorder
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-20 21:54
 */
public interface GunRICStateRecorder {
    int reConnectionTimes();

    /**
     * clean the connection state
     */
    void clean();

    void update();

    default ConnectionState state() {
        return ConnectionState.reTimesToState(reConnectionTimes());
    }


    enum ConnectionState {
        /**
         * STABLIZE
         * NSTABLE
         * HARDLY
         * LOSTCONECTION;
         */
        STABLIZE(0, 0), NSTABLE(1, 4), HARDLY(5, 8), LOSTCONECTION(9, Integer.MAX_VALUE);
        int minTimes;
        int maxTimes;

        ConnectionState(int minTimes, int maxTimes) {
            this.minTimes = minTimes;
            this.maxTimes = maxTimes;
        }

        static ConnectionState reTimesToState(int val) {
            for (ConnectionState state : values()) {
                if (state.maxTimes >= val && state.minTimes <= val) {
                    return state;
                }
            }
            return LOSTCONECTION;
        }
    }
}
