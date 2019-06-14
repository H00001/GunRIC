package top.gunplan.ric.common;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author dosdrtt
 */
public abstract class AbstractGunRicCommonSocket extends Socket implements Delayed {
    private boolean isUsed = true;

    AbstractGunRicCommonSocket(String addr, int port) throws IOException {
        super(addr, port);
    }


    /**
     * set delay time
     *
     * @param unit time small unit
     * @return long to execute
     */
    @Override
    public abstract long getDelay(TimeUnit unit);


    /**
     * compare to another object
     *
     * @param o another object
     * @return SUCCEED
     */
    @Override
    public abstract int compareTo(Delayed o);

    public boolean isUsed() {
        return isUsed;
    }

    public void setNoUsed() {
        isUsed = false;
    }

    public void setUsed() {
        isUsed = false;
    }

    public void sendTcpData(byte[] bytes) throws IOException {
        this.getOutputStream().write(bytes);
    }


    public byte[] receiveTcpData() throws IOException {
        return GunRicBufferRead.bufferRead(this.getInputStream());
    }
}
