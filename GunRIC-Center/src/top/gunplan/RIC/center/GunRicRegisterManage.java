package top.gunplan.RIC.center;

import top.gunplan.RIC.center.property.GunRicCenterServiceUtilProperty;
import top.gunplan.netty.common.GunNettyPropertyManagerImpl;
import top.gunplan.ric.protocol.RicProtocolParamType;
import top.gunplan.ric.protocol.util.PathUtil;
import top.gunplan.utils.AbstractGunBaseLogUtil;
import top.gunplan.utils.GunBytesUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import java.util.List;

/**
 * @author dosdrtt
 * @nonconcurrent
 */
public final class GunRicRegisterManage {
    private static GunRicCenterServiceUtilProperty property = null;
    private static String PARFOL;

    public static boolean loadRegister() throws IOException {
        property = GunNettyPropertyManagerImpl.getProperty("ric-center-services-util");
        assert property != null;
        PARFOL = PathUtil.getRes() + property.getServicespath();
        findServices(Paths.get(PARFOL));
        return true;
    }

    public static void findServices(Path startingDir) throws IOException {
        Files.walkFileTree(startingDir, new FindJavaVisitor());
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {


        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toFile().isFile()) {
                final String interfacename = file.getParent().toString().replace(PARFOL + "/", "").replace("/", ".");
                final String methodname = file.toFile().getName().split("_")[0];
                GunBytesUtil.GunReadByteStream stream = new GunBytesUtil.GunReadByteStream(Files.readAllBytes(file));
                int paramlen = stream.readByte();
                RicProtocolParamType[] type = new RicProtocolParamType[paramlen];
                for (int i = 0; i < paramlen; i++) {
                    type[i] = RicProtocolParamType.valuefrom(stream.readByte());
                }
                stream.readByte();
                List<InetSocketAddress> addresses = new ArrayList<>(1);
                String addr;

                for (; (addr = stream.readLine()) != null; ) {
                    addresses.add(new InetSocketAddress(addr.split(property.getDivideflag())[0], Integer.parseInt(addr.split(property.getDivideflag())[1])));
                }

                GunRicInterfaceBuffer.GunRicCdtInterface key = new GunRicInterfaceBuffer.GunRicCdtInterface(type, interfacename, methodname);
                AbstractGunBaseLogUtil.debug("find local services " + interfacename + "." + methodname);
                GunRicInterfaceBuffer.intermapping.put(key, addresses);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
