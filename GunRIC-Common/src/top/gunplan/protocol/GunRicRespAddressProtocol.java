package top.gunplan.protocol;

import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.utils.GunBytesUtil;

import java.util.ArrayList;
import java.util.List;

public class GunRicRespAddressProtocol extends AbstractGunRicProtocol implements GunRicOutputHelper {
    public GunRicRespAddressProtocol() {
        this.type = RicProtocolType.GET;
        this.code = RicProtocolCode.GET_RES;
    }

    private List<AddressItem> addressItems = new ArrayList<>(1);

    public void pushAddress(AddressItem ad) {
        this.addressItems.add(ad);
    }

    @Override
    public boolean unSerialize(byte[] bytes) {
        GunBytesUtil.GunReadByteStream util = new GunBytesUtil.GunReadByteStream(bytes);
        publicUnSet(util);
        int len = util.readByte();
        for (int i = 0; i < len; i++) {
            //addressItems.get(i).unSerialize()
        }


        return checkEnd(util) && checKNext(util);
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
        return new byte[0];
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
            //addressItems.get(i).unSerialize()
        }


        return checkEnd(util) && checKNext(util);
    }

    public static class AddressItem implements GunNetOutputInterface, GunNetInputInterface {

        static final int FLAG_LEN = 8;
        static final int ADD_LEN = 8;
        static final int PORT_LEN = 2;
        static final int NEED_SPACE = FLAG_LEN + ADD_LEN + PORT_LEN;
        private byte[] flag = new byte[FLAG_LEN];
        private int port;
        private String address;

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
            return new byte[0];
        }

        @Override
        public boolean unSerialize(byte[] bytes) {
            return false;
        }
    }
}
