package top.gunplan.ric.center;

import top.gunplan.ric.center.common.GunRicCenterStaticPath;
import top.gunplan.ric.center.property.GunRicCenterServiceUtilProperty;
import top.gunplan.netty.impl.GunNettyPropertyManagerImpl;
import top.gunplan.ric.protocol.BaseGunRicCdt;
import top.gunplan.ric.protocol.GunAddressItem4;
import top.gunplan.ric.protocol.GunRicCdtImpl;
import top.gunplan.ric.protocol.RicProtocolParamType;
import top.gunplan.utils.AbstractGunBaseLogUtil;
import top.gunplan.utils.GunBytesUtil;

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
        property = GunNettyPropertyManagerImpl.getProperty(GunRicCenterServiceUtilProperty.class);
        assert property != null;
        try {

            findServices(Paths.get(GunRicCenterStaticPath.SERVICES_PATH));
        } catch (IOException exp) {
            AbstractGunBaseLogUtil.error(exp);
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
                final String interfacename = file.getParent().toString().replace(GunRicCenterStaticPath.SERVICES_PATH + "/", "").replace("/", ".");
                final String methodname = file.toFile().getName().split("_")[0];
                GunBytesUtil.GunReadByteStream stream = new GunBytesUtil.GunReadByteStream(Files.readAllBytes(file));
                int paramlen = stream.readByte();
                RicProtocolParamType[] type = new RicProtocolParamType[paramlen];
                for (int i = 0; i < paramlen; i++) {
                    type[i] = RicProtocolParamType.valuefrom(stream.readByte());
                }
                stream.readByte();
                String addr;
                for (; (addr = stream.readLine()) != null; ) {
                    BaseGunRicCdt key = new GunRicCdtImpl(type, interfacename, methodname);
                    GunRicCenterStdRecordManage.Instance.getHinstance().doRegex(key, new GunAddressItem4(addr.split(property.getDivideFlag())[0], Integer.parseInt(addr.split(property.getDivideFlag())[1])));
                }
                AbstractGunBaseLogUtil.debug("find local services " + interfacename + "." + methodname);

            }
            return FileVisitResult.CONTINUE;
        }
    }
}
