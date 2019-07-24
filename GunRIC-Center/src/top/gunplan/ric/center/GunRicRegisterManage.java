package top.gunplan.ric.center;

import top.gunplan.netty.GunNettySystemServices;
import top.gunplan.ric.center.common.GunRicCenterStaticPath;
import top.gunplan.ric.center.context.F;
import top.gunplan.ric.center.property.GunRicCenterServiceUtilProperty;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author dosdrtt
 * @nonconcurrent
 */
public final class GunRicRegisterManage {
    private static GunRicCenterServiceUtilProperty property = null;

    public static boolean loadRegister() {
        property = GunNettySystemServices.PROPERTY_MANAGER.acquireProperty(GunRicCenterServiceUtilProperty.class);
        assert property != null;
        try {
            findServices(Paths.get(GunRicCenterStaticPath.SERVICES_PATH));
        } catch (IOException exp) {
            F.LOG.error(exp);
        }
        return true;

    }

    private static void findServices(Path startingDir) throws IOException {
        Files.walkFileTree(startingDir, new FindJavaVisitor());
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {


        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toFile().isFile()) {
                final String interfaceName = file.getParent().toString().replace(GunRicCenterStaticPath.SERVICES_PATH + "/", "").replace("/", ".");
                final String methodName = file.toFile().getName().split("_")[0];
                F.LOG.debug("find history local services " + interfaceName + "." + methodName);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
