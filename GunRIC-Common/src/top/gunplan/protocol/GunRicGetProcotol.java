package top.gunplan.protocol;

/**
 * @author dosdrtt
 */
public class GunRicGetProcotol extends GunRicRegisterProtocol {
    //todo
    public GunRicGetProcotol() {
        this.type = RicProtocolType.GET;
        this.setPort(0x0);
    }
}
