package top.gunplan.ric.center.manage;


import java.util.List;

public interface GunRicConsumerManage extends GunRicClientManager<GunRicConsumerClient> {

    List<GunRicConsumerClient> aireSavoirConsumers();

}
