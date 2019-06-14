package top.gunplan.ric.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * this class is used do divide different package
 *
 * @author dosdrtt
 * @date 1557303969
 */
public final class GunRicTypeDividePacketManage {
    public static AbstractGunRicProtocol findPackage(byte[] bytes) {
        GunBytesUtil.GunReadByteStream util = new GunBytesUtil.GunReadByteStream(bytes);
        return findPackage(util);
    }

    static AbstractGunRicProtocol findPackage(GunBytesUtil.GunReadByteStream util) {
        RicProtocolType retype = RicProtocolType.valuefrom(util.readInt());
        AbstractGunRicProtocol protocol = null;
        assert retype != null;
        switch (retype) {
            case HELLO: {
                RicProtocolCode code = RicProtocolCode.valueFrom(util.readInt());
                protocol = code == RicProtocolCode.HELLO_REQ ? new GunRicHelloProtocol(true) : new GunRicHelloProtocol(false);
            }
            util.pSub2();
            break;
            case REGRESP:
                protocol = new GunRicRegisterStatusProtocol();
                break;
            case REQUEST:
                protocol = new GunRicInputProtocol();
                break;
            case REGISTER: {
                protocol = new GunRicRegisterProtocol();
            }
            break;
            case GET: {
                RicProtocolCode code = RicProtocolCode.valueFrom(util.readInt());
                protocol = code == RicProtocolCode.GET_REQ ? new GunRicGetAddressProtocol() : new GunRicRespAddressProtocol();
            }
            util.pSub2();
            break;
            case RESPONSE:
                protocol = new GunRicOutputProtocol();
            default:
                break;
        }
        util.pSub2();
        return protocol;
    }
}
