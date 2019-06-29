package top.gunplan.ric.user;


import top.gunplan.ric.common.GunRicCommonBuffered;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.utils.GunRicBufferRead;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

/**
 * @author dosdrtt
 */
class GunRicUserUsedConnection extends AbstractRicUserConnection {
    private GunRicCommonBuffered<GunRicUserClassRec> buffer = GunRicInterfaceBuffer.newInstance();
    private List<GunAddressItemInterface> addresss;

    void getAddressItem(Method method) throws IOException {
        socket.getOutputStream().write(new GunRicGetAddressProtocol(method).serialize());
        byte[] pt = GunRicBufferRead.bufferRead(socket.getInputStream());
        GunRicRespAddressProtocol protocol = new GunRicRespAddressProtocol();
        protocol.unSerialize(pt);
        this.buffer.push(new GunRicUserClassRec(method.getDeclaringClass()), protocol.getAddressItems());
        this.addresss = protocol.getAddressItems();
        AbstractGunBaseLogUtil.debug("address has finded ");
    }

    GunRicOutputProtocol send(GunRicInputProtocol protocol) throws IOException {
        Socket ss = GunRicUserConnectionFactory.newSocket(this.addresss.get(0).getInet());
        ss.getOutputStream().write(protocol.serialize());
        byte[] pt = GunRicBufferRead.bufferRead(ss.getInputStream());
        GunRicOutputProtocol output = (GunRicOutputProtocol) GunRicTypeDividePacketManage.findPackage(pt);
        output.unSerialize(pt);
        return output;
    }

}
