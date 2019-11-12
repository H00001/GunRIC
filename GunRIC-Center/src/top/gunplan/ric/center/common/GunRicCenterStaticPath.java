package top.gunplan.ric.center.common;

import top.gunplan.netty.GunNettySystemService;
import top.gunplan.ric.center.property.GunRicCenterServicesProperty;
import top.gunplan.ric.protocol.util.PathUtil;


/**
 * @author dosdrtt
 */
public class GunRicCenterStaticPath {
    public static final String SERVICES_PATH = PathUtil.getRes() + GunNettySystemService.PROPERTY_MANAGER.acquireProperty(GunRicCenterServicesProperty.class).getServicespath();
}
