package top.gunplan.ric.center.contest;

/**
 * GunRicCenterInformationImpl
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-19 19:54
 */
public class GunRicCenterInformationImpl implements GunRicCenterInformation {
    private final short id;
    private final String name;
    private final GunRicCenterInformationAttachObject attach;
    private GunRicCenterRole role;

    public GunRicCenterInformationImpl(short id, String name) {
        this(id, name, null);
    }


    public GunRicCenterInformationImpl(short id, String name, GunRicCenterInformationAttachObject attach) {
        this.id = id;
        this.name = name;
        this.attach = attach;
    }

    @Override
    public short id() {
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
    public GunRicCenterRole role() {
        return role;
    }

    @Override
    public void changeRole(GunRicCenterRole role) {
        this.role = role;
    }
}
