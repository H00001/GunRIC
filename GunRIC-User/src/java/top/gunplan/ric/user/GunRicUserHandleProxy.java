package top.gunplan.ric.user;


import top.gunplan.ric.protocol.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

/**
 * @author dosdrtt
 * @version 0.0.0.1
 * @since 0.0.0.1
 */

public class GunRicUserHandleProxy implements InvocationHandler {
    private GunRicUserProperty property = GunRicUserPropertyManageImpl.getProperty();

    private List<GunAddressItem> getAddress(Method method) throws IOException {
        List<GunAddressItem> addressItems;
        if ((addressItems = GunRicUserRemoteAddressManage.mmap.get(method.getDeclaringClass().getName())) == null) {
            addressItems = sendTOCenterToFind(method);
        }
        return addressItems;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<GunAddressItem> addressItems = getAddress(method);
        Socket ss = GunRicUserConncetionFactory.newSocket(addressItems.get(0).getAddress(), addressItems.get(0).getPort());
        sendMessage(method, args, ss.getOutputStream());
        Object result = receiveMessage(ss.getInputStream(), 0);
        ss.close();
        return result;

    }

    private Object receiveMessage(InputStream in, long sernum) throws IOException {
        byte[] b = new byte[2014];
        int len = in.read(b);
        byte[] newb = new byte[len];
        System.arraycopy(b, 0, newb, 0, len);
        GunRicOutputProtocol output = (GunRicOutputProtocol) GunRicTypeDividePacketManage.findPackage(newb);
        output.unSerialize(newb);
        if (output.getCode() == RicProtocolCode.FAIL) {
            System.out.println(output.getReturnValue().obj);
            return null;
        } else if (output.getCode() == RicProtocolCode.SUCCEED) {
            if (output.getSerialnumber() == sernum) {
                return output.getReturnValue().obj;
            }
        }
        return output.getReturnValue().obj;
    }

    private void sendMessage(Method method, Object[] args, OutputStream out) throws IOException {
        GunRicInputProtocol input = new GunRicInputProtocol();
        input.setSerialnumber(0);
        input.setInameMname(method);
        if (args != null) {
            input.pushParams(args);
        }
        out.write(input.serialize());
    }

    private List<GunAddressItem> sendTOCenterToFind(Method method) throws IOException {
        Socket so = GunRicUserConncetionFactory.newSocket(property.getAddress()[0]);
        GunRicGetAddressProtocol proctol = new GunRicGetAddressProtocol();
        proctol.setInameMname(method);
        proctol.pushParamTypes(method.getParameterTypes());
        so.getOutputStream().write(proctol.serialize());
        byte[] buffer = new byte[1024];
        int len = so.getInputStream().read(buffer);
        so.close();
        byte[] b1 = new byte[len];
        System.arraycopy(buffer, 0, b1, 0, len);
        GunRicRespAddressProtocol protocol = new GunRicRespAddressProtocol();
        protocol.unSerialize(b1);
        GunRicUserRemoteAddressManage.mmap.put(method.getDeclaringClass().getName(), protocol.getAddressItems());
        return protocol.getAddressItems();
    }
}
