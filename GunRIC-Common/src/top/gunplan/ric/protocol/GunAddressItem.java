package top.gunplan.ric.protocol;

import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.GunBytesUtil;

import java.net.InetSocketAddress;
import java.util.Arrays;

/**
 * @author dosdrtt
 */
public class GunAddressItem implements GunAddressItemInterface, GunNetOutputInterface, GunNetInputInterface, GunRicOutputHelper {

    private static final int FLAG_LEN = 8;
    private static final int ADD_LEN = 8;
    private static final int PORT_LEN = 2;
    static final int NEED_SPACE = FLAG_LEN + ADD_LEN + PORT_LEN;
    private byte[] flag = new byte[FLAG_LEN];
    private InetSocketAddress address;

    private void readFlag(GunBytesUtil.GunReadByteStream stream) {
        this.flag = stream.readByte(FLAG_LEN);
    }

    public GunAddressItem(InetSocketAddress address) {
        this.address = address;
    }

    public GunAddressItem(String address, int port) {
        this(new InetSocketAddress(address, port));
    }

    private void readAddress(GunBytesUtil.GunReadByteStream stream) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int v = stream.readInt();
            sb.append(v).append(".");
        }

        this.address = new InetSocketAddress(sb.toString().substring(0, sb.length() - 1), stream.readInt());
    }

    public GunAddressItem() {

    }


    @Override
    public InetSocketAddress getInet() {
        return this.address;
    }

    @Override
    public int getPort() {
        return this.address.getPort();
    }


    @Override
    public String getAddress() {
        return address.getHostString();
    }

    @Override
    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }

    @Override
    public byte[] serialize() {
        GunBytesUtil.GunWriteByteStream util = createSpace();
        util.write(flag);
        String[] list = address.getHostString().split("\\.");
        Arrays.stream(list).forEach((var) -> {
            int v = Integer.parseInt(var);
            util.write(v);
        });
        util.write(address.getPort());
        return util.getInput();
    }


    @Override
    public boolean unSerialize(byte[] bytes) {
        GunBytesUtil.GunReadByteStream util = new GunBytesUtil.GunReadByteStream(bytes);
        unSerialize(util);
        return true;
    }


    public boolean unSerialize(GunBytesUtil.GunReadByteStream stream) {
        readFlag(stream);
        readAddress(stream);

        return true;
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        return new GunBytesUtil.GunWriteByteStream(new byte[NEED_SPACE]);
    }
}
