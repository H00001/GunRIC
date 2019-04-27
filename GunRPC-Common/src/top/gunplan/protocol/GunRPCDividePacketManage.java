package top.gunplan.protocol;



import top.gunplan.utils.GunBytesUtil;

public final class GunRPCDividePacketManage {
    public static <T extends AbstractGunRPCProtocl> T findPackage(byte[] bytes) {
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
                protocl = new GunRPCHelloProtocl(true);
                break;
            case REQUEST:
                protocl = new GunRPCInputProtocl();
                break;
            case REGISTER:
                protocl = new GunRPCOutputProtocl();
                break;
            case RESPONSE:
                protocl = new GunRPCOutputProtocl();
            default:
                break;
        }
        protocl.unSerialize(bytes);
        return (T) (protocl);
    }
}
