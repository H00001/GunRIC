package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * this class is used do divide different package
 *
 * @author dosdrtt
 * @date 1557303969
 */
public final class GunRicTypeDividePacketManage {
    public static AbstractGunRicProtocol findPackage(byte[] bytes) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(bytes);
        RicProtocolType retype = RicProtocolType.valuefrom(util.readInt());
        AbstractGunRicProtocol protocol = null;
        assert retype != null;
        switch (retype) {
            case HELLO:
                protocol = new GunRicHelloProtocol();
                break;
            case REGRESP:
                protocol = new GunRicRegisterStatusProtocol();
                break;
            case REQUEST:
                protocol = new GunRicInputProtocol();
                break;
            case REGISTER:
                protocol = new GunRicRegisterProtocol();
                break;
            case RESPONSE:
                protocol = new GunRicOutputProtocol();
            default:
                break;
        }
        assert protocol != null;
        protocol.unSerialize(bytes);
        return protocol;
    }
}
