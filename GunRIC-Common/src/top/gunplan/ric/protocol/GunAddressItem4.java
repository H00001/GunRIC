package top.gunplan.ric.protocol;

import top.gunplan.ric.common.GunRicMethodHash;
import top.gunplan.utils.GunBytesUtil;

import java.net.InetSocketAddress;
import java.util.Arrays;

/**
 * @author dosdrtt
 */
public class GunAddressItem4 implements GunAddressItemInterface {


    private static final int ADDR_LEN = 0b100;

    static final int NEED_SPACE = FLAG_LEN + ADD_LEN + PORT_LEN;
    private byte[] flag = new byte[FLAG_LEN];
    private InetSocketAddress address;

    private void readFlag(GunBytesUtil.GunReadByteStream stream) {
        this.flag = stream.readByte(FLAG_LEN);
    }

    public GunAddressItem4(InetSocketAddress address) {
        this.address = address;
    }

    public GunAddressItem4(String address, int port) {
        this(new InetSocketAddress(address, port));
    }

    public GunAddressItem4() {

    }

    private void readAddress(GunBytesUtil.GunReadByteStream stream) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ADDR_LEN; i++) {
            int v = stream.readInt();
            sb.append(v).append(".");
        }

        this.address = new InetSocketAddress(sb.toString().substring(0, sb.length() - 1), stream.readInt());
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

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream stream) {
        readFlag(stream);
        readAddress(stream);
        return true;
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        return new GunBytesUtil.GunWriteByteStream(new byte[NEED_SPACE]);
    }


    @Override
    public int hashCode() {
        return GunRicMethodHash.Instance.getHashInstance().h(getAddress(), "", null) ^ getPort();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GunAddressItemInterface) {
            if (hashCode() == obj.hashCode()) {
                GunAddressItemInterface s = (GunAddressItemInterface) obj;
                return getAddress().equals(s.getAddress()) && getPort() == s.getPort();
            }
        }
        return false;
    }
}
