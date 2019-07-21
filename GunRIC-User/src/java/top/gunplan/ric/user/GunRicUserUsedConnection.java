package top.gunplan.ric.user;


import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.ric.common.GunRicCommonBuffered;
import top.gunplan.ric.common.GunRicInterfaceBuffer;
import top.gunplan.ric.protocol.*;
import top.gunplan.ric.stand.GunRicInvokeReqStand;
import top.gunplan.ric.stand.GunRicInvokeResStand;
import top.gunplan.ric.stand.GunRicRetAddressStand;
import top.gunplan.ric.utils.GunRicBufferUtils;


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
        byte[] pt = GunRicBufferUtils.READER.bufferRead(socket.getInputStream());
        GunRicRetAddressStand protocol = new GunRicRespAddressProtocol();
        protocol.unSerialize(pt);
        this.buffer.push(new GunRicUserClassRec(method.getDeclaringClass()), protocol.addressItems());
        this.addresss = protocol.addressItems();
        GunNettyContext.logger.debug("address has finded ");

    }

    GunRicInvokeResStand send(GunRicInvokeReqStand protocol) throws IOException {
        Socket ss = GunRicUserConnectionFactory.newSocket(this.addresss.get(0).getInet());
        ss.getOutputStream().write(protocol.serialize());
        byte[] pt = GunRicBufferUtils.READER.bufferRead(ss.getInputStream());
        GunRicInvokeResStand output = (GunRicInvokeResStand) GunRicTypeDividePacketManage.findPackage(pt);
        output.unSerialize(pt);
        return output;
    }

}
