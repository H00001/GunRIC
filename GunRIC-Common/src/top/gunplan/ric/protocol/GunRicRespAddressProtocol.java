package top.gunplan.ric.protocol;

import top.gunplan.ric.stand.GunRicRetAddressStand;
import top.gunplan.utils.GunBytesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dosdrtt
 */
public class GunRicRespAddressProtocol extends AbstractGunRicProtocol implements GunRicOutputHelper, GunRicRetAddressStand {


    public GunRicRespAddressProtocol() {
        this.type = RicProtocolType.GET;
        this.code = RicProtocolCode.GET_RES;
    }

    private List<GunAddressItemInterface> addressItems = new ArrayList<>(1);

    GunRicRespAddressProtocol(List<GunAddressItemInterface> addresses) {
        this();
        addresses.forEach(this::pushAddress);
    }

    @Override
    public List<GunAddressItemInterface> addressItems() {
        return addressItems;
    }

    @Override
    public void pushAddress(GunAddressItemInterface ad) {
        this.addressItems.add(ad);
    }

    public void pushAddressList(List<GunAddressItemInterface> addresses) {
        addresses.forEach(this::pushAddress);
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
        for (GunAddressItemInterface item : addressItems) {
            util.write(item.serialize());
        }
        util.write(END_FLAG);
        return util.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        int lenneed = addressItems.size() * GunAddressItemInterface.NEED_SPACE;
        lenneed += TYPE_LEN + CODE_LEN + END_FLAG.length + SERIALIZE_LEN + 1;
        return new GunBytesUtil.GunWriteByteStream(new byte[lenneed]);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {

        publicUnSet(util);
        int len = util.readByte();
        for (int i = 0; i < len; i++) {
            GunAddressItem4 item = new GunAddressItem4();
            item.unSerialize(util);
            addressItems.add(item);
            //addressItems.get(i).unSerialize()
        }
        return checkEnd(util) && checkNext(util);
    }


}
