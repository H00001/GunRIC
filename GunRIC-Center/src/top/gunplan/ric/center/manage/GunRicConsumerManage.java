package top.gunplan.ric.center.manage;


import java.util.List;

public interface GunRicConsumerManage extends GunRicClientManage<GunRicConsumerClient> {

    List<GunRicConsumerClient> aireSavoirConsumers();

}
