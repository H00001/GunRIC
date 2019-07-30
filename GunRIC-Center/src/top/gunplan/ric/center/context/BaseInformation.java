package top.gunplan.ric.center.context;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public final class BaseInformation {
    public static GunRicCenterInformation information;

    public static AtomicReference<Set<GunRicCenterInformation>> slaves = new AtomicReference<>(null);

    static {

    }
}
