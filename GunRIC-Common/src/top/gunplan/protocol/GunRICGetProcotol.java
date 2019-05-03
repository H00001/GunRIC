package top.gunplan.protocol;

public class GunRICGetProcotol extends GunRICRegisterProtocol {
    public GunRICGetProcotol() {
        this.type = RPCProtoclType.GET;
        this.setPort(0x0);
    }
}
