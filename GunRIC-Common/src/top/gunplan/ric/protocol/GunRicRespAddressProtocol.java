package top.gunplan.ric.protocol;

import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.GunBytesUtil;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dosdrtt
 */
public class GunRicRespAddressProtocol extends AbstractGunRicProtocol implements GunRicOutputHelper {


    public GunRicRespAddressProtocol() {
        this.type = RicProtocolType.GET;
        this.code = RicProtocolCode.GET_RES;
    }

    private List<AddressItem> addressItems = new ArrayList<>(1);

    public void pushAddress(AddressItem ad) {
        this.addressItems.add(ad);
    }

    public void pushAddressList(List<InetSocketAddress> addresses) {

        addresses.forEach(addr -> pushAddress(new AddressItem(addr)));

    }

    @Override
    public boolean unSerialize(byte[] bytes) {
        /**
         * this is a bug, but no one know the reason
         */
        addressItems.clear();
        GunBytesUtil.GunReadByteStream util = new GunBytesUtil.GunReadByteStream(bytes);
        return unSerialize(util);

    }

    @Override
    public byte[] serialize() {
        GunBytesUtil.GunWriteByteStream util = createSpace();
        publicSet(util);
        util.writeByte((byte) addressItems.size());
        for (AddressItem item : addressItems) {
            util.write(item.serialize());
        }
        util.write(END_FLAG);
        return util.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        int lenneed = addressItems.size() * AddressItem.NEED_SPACE;
        lenneed += TYPE_LEN + CODE_LEN + END_FLAG.length + SERIALNUM_LEN + 1;
        return new GunBytesUtil.GunWriteByteStream(new byte[lenneed]);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {

        publicUnSet(util);
        int len = util.readByte();
        for (int i = 0; i < len; i++) {
            AddressItem item = new AddressItem();
            item.unSerialize(util);
            addressItems.add(item);
            //addressItems.get(i).unSerialize()
        }
        return checkEnd(util) && checKNext(util);
    }

    public static class AddressItem implements GunNetOutputInterface, GunNetInputInterface, GunRicOutputHelper {

        static final int FLAG_LEN = 8;
        static final int ADD_LEN = 8;
        static final int PORT_LEN = 2;
        static final int NEED_SPACE = FLAG_LEN + ADD_LEN + PORT_LEN;
        private byte[] flag = new byte[FLAG_LEN];
        private int port;
        private String address;

        private void readFlag(GunBytesUtil.GunReadByteStream stream) {
            this.flag = stream.readByte(FLAG_LEN);
        }

        public AddressItem(InetSocketAddress address) {
            this.port = address.getPort();
            this.address = address.getHostString();
        }

        public AddressItem() {

        }

        private void readAddress(GunBytesUtil.GunReadByteStream stream) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                int v = stream.readInt();
                sb.append(v).append(".");
            }
            this.address = sb.toString().substring(0, sb.length() - 1);
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            if (port != 0) {
                this.port = port;
            }
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public byte[] serialize() {
            GunBytesUtil.GunWriteByteStream util = createSpace();
            util.write(flag);
            String[] list = address.split("\\.");
            Arrays.stream(list).forEach((var) -> {
                int v = Integer.parseInt(var);
                util.write(v);
            });
            util.write(port);
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
            this.port = stream.readInt();
            return true;
        }

        @Override
        public GunBytesUtil.GunWriteByteStream createSpace() {
            return new GunBytesUtil.GunWriteByteStream(new byte[NEED_SPACE]);
        }
    }
}
