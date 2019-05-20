package top.gunplan.RIC.center;

import top.gunplan.ric.protocol.util.PathUtil;
import top.gunplan.utils.GunBytesUtil;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dosdrtt
 * @nonconcurrent
 */
public final class GunRicRegisterManage {
    private final static String PARFOL = PathUtil.getRes() + "/" + "services";

    public static boolean loadRegister() throws IOException {
        findServices(Paths.get(PARFOL));
        return true;
    }

    public static void findServices(Path startingDir) throws IOException {
        Files.walkFileTree(startingDir, new FindJavaVisitor());
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {


        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toFile().isFile()) {
                final String interfacename = file.toFile().getPath().replace(PARFOL, "");
                final String methodname = file.toFile().getName().split("_")[0];
                final int id = Integer.parseInt(file.toFile().getName().split("_")[1]);
                GunBytesUtil.GunReadByteStream stream = new GunBytesUtil.GunReadByteStream(Files.readAllBytes(file));

                GunRicInterfaceBuffer.GunRicCdtInterface key = new GunRicInterfaceBuffer.GunRicCdtInterface();
                GunRicInterfaceBuffer.intermapping.put()
                result.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
