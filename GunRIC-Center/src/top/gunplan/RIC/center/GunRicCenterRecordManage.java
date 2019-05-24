package top.gunplan.RIC.center;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 */
public interface GunRicCenterRecordManage {
    void addRecord(File file, GunRicInterfaceBuffer.GunRicCdtInterface g, InetSocketAddress address) throws FileNotFoundException, IOException;
}
