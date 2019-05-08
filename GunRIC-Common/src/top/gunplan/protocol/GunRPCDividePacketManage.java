package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

public final class GunRPCDividePacketManage {
    public static AbstractGunRPCProtocl findPackage(byte[] bytes) {
        GunBytesUtil.GunReadByteUtil util = new GunBytesUtil.GunReadByteUtil(bytes);
        RPCProtoclType retype = RPCProtoclType.valuefrom(util.readInt());
        AbstractGunRPCProtocl protocl = null;
        assert retype != null;
        switch (retype) {
            case PING:
                protocl = new GunRPCHelloProtocl(true);
                break;
            case PONG:
                protocl = new GunRPCHelloProtocl(false);
                break;
            case REGRESP:
                protocl = new GunRicRegisterStatusProtocol();
                break;
            case REQUEST:
                protocl = new GunRicInputProtocol();
                break;
            case REGISTER:
                protocl = new GunRICRegisterProtocol();
                break;
            case RESPONSE:
                protocl = new GunRPCOutputProtocl();
            default:
                break;
        }
        protocl.unSerialize(bytes);
        return protocl;
    }
}
