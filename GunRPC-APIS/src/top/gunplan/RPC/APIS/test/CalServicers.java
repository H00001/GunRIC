package top.gunplan.RPC.APIS.test;

import top.gunplan.RPC.APIS.test.anno.GunUseImpl;

@GunUseImpl(impl = "top.gunplan.services.CalCulServicesImpl")
public interface CalServicers {
    int add(int a,int b);
    int sub(int a,int b);
    int multiplication(int a,int b);
    int division(int a,int b);
}
