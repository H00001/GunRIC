package top.gunplan.ric.center.common;

import top.gunplan.ric.center.property.GunRicCenterServicesProperty;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.ric.protocol.util.PathUtil;


/**
 * @author dosdrtt
 */
public class GunRicCenterStaticPath {
    public static final String SERVICES_PATH = PathUtil.getRes() + ((GunRicCenterServicesProperty) GunNettyPropertyManagerImpl.getProperty(GunRicCenterServicesProperty.class)).getServicespath();
}
