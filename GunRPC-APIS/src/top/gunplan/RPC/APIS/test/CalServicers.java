package top.gunplan.RPC.APIS.test;

import top.gunplan.RPC.APIS.test.anno.GunUseImpl;

@GunUseImpl(impl = "top.gunplan.services.FastAdd")
public interface CalServicers {
    public int add(int a,int b);
}
