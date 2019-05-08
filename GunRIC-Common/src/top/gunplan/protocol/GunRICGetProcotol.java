package top.gunplan.protocol;

public class GunRICGetProcotol extends GunRicRegisterProtocol {
    //todo
    public GunRICGetProcotol() {
        this.type = RicProtoclType.GET;
        this.setPort(0x0);
    }
}
