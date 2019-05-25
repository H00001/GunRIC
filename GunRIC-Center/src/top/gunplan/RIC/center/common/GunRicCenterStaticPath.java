package top.gunplan.RIC.center.common;

import top.gunplan.RIC.center.property.GunRicCenterServicesProperty;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.ric.protocol.util.PathUtil;


/**
 * @author dosdrtt
 */
public class GunRicCenterStaticPath {
    public static final String SERVICES_PATH = PathUtil.getRes() + ((GunRicCenterServicesProperty) GunNettyPropertyManagerImpl.getProperty("ric-center-services")).getServicespath();
}
