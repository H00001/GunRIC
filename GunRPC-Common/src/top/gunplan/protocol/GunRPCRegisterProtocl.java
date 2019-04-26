package top.gunplan.protocol;




public class GunRPCRegisterProtocl extends AbstractGunRPCProtocl {
    public GunRPCRegisterProtocl() {
        this.type = RPCProtoclType.REGISTER;
    }

    @Override
    public boolean unSerialize(byte[] in) {
        return false;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }
}
