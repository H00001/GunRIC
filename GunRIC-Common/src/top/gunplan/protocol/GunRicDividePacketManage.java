package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 * @date 1557303969
 */
public final class GunRicDividePacketManage {
    public static AbstractGunRPCProtocl findPackage(byte[] bytes) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(bytes);
        RicProtoclType retype = RicProtoclType.valuefrom(util.readInt());
        AbstractGunRPCProtocl protocol = null;
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
                protocol = new GunRicOutputProtocl();
            default:
                break;
        }
        assert protocol != null;
        protocol.unSerialize(bytes);
        return protocol;
    }
}
