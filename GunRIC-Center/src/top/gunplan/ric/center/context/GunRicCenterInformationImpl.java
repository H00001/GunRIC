package top.gunplan.ric.center.context;

import top.gunplan.netty.GunProperty;
import top.gunplan.netty.anno.GunPropertyMap;
import top.gunplan.ric.protocol.GunAddressItemInterface;

/**
 * GunRicCenterInformationImpl
 *
 * @author frank albert
 * @version 0.0.0.1
 * #date 2019-07-19 19:54
 */

@GunPropertyMap(name = "ric-center-node-master")
public class GunRicCenterInformationImpl implements GunRicCenterInformation, GunProperty {
    private int id;
    private String name;
    private GunRicCenterInformationAttachObject attach;
    private GunRicCenterRole role = GunRicCenterRole.NONE;
    private String masterNode;

    public String getMasterNode() {
        return masterNode;
    }

    public GunRicCenterInformationImpl() {

    }

    public GunRicCenterInformationImpl(short id, String name) {
        this(id, name, null);
    }


    public GunRicCenterInformationImpl(short id, String name, GunRicCenterInformationAttachObject attach) {
        this.id = id;
        this.name = name;
        this.attach = attach;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public GunRicCenterInformationAttachObject attach() {
        return attach;
    }

    @Override
    public GunAddressItemInterface address() {
        return null;
    }

    @Override
    public GunRicCenterRole role() {
        if ("self".equals(masterNode)) {
            this.role = GunRicCenterRole.MASTER;
        }
        return this.role;
    }

    @Override
    public void changeRole(GunRicCenterRole role) {
        this.role = role;
    }

    @Override
    public boolean doRegex() {
        return true;
    }

    @Override
    public boolean isAvailable() {
        if (role == GunRicCenterRole.MASTER) {
            GunCenterInformationManager.updateMasterSelf(this);
        }
        return doRegex();
    }
}
