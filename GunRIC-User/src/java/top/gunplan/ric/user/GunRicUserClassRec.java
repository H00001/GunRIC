package top.gunplan.ric.user;

import top.gunplan.ric.common.GunRicCommonExeIst;

/**
 * @author dosdrtt
 */
public class GunRicUserClassRec implements GunRicCommonExeIst {
    private Class<?> inName;

    public GunRicUserClassRec(Class<?> inName) {
        this.inName = inName;
    }

    public Class<?> getInName() {
        return inName;
    }

    public void setInName(Class<?> inName) {
        this.inName = inName;
    }
}
