package top.gunplan.protocol;

public class GunRICGetProcotol extends GunRicRegisterProtocol {
    //todo
    public GunRICGetProcotol() {
        this.type = RicProtocolType.GET;
        this.setPort(0x0);
    }
}
