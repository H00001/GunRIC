package top.gunplan.ric.center.manage;


import java.util.Set;

public interface GunRicConsumerManage extends GunRicClientManager<GunRicConsumerClient> {

    Set<GunRicConsumerClient> aireSavoirConsumers();

}
