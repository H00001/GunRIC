package top.gunplan.protocol;

import com.sun.org.apache.xpath.internal.operations.Bool;
import top.gunplan.utils.GunBytesUtil;

import java.io.Serializable;
import java.util.Stack;

/**
 *
 */
public abstract class AbstractGunRPCProtocl implements GunNetInputInterface, GunNetOutputInterface {
//    @Test
//    public void test() {
//        AbstractGunRPCProtocl it = new AbstractGunRPCProtocl();
//        it.setInterfaceName("hello");
//        it.setMethodName("rpc");
//        it.setType(RPCProtoclType.REQUEST);
//        it.setCode(RPCProtoclCode.SUCCEED);
//        it.pushParam("1234");
//
//        byte[] bom = it.serialize();
//        AbstractGunRPCProtocl it2 = new AbstractGunRPCProtocl();
//        it2.unSerialize(bom);
//        System.out.println("dd");
//    }

    // type 2 method 2 interfaceNamelen 1 interfacename ? methodNamelen 1 methodNamel? paramlen 1 end 2
    RPCProtoclType type;
    RPCProtoclCode code;


    public RPCProtoclType getType() {
        return type;
    }

    public void setType(RPCProtoclType type) {
        this.type = type;
    }

    public RPCProtoclCode getCode() {
        return code;
    }

    public void setCode(RPCProtoclCode code) {
        this.code = code;
    }

    final byte[] endFlage = {0x0a, 0x05};


    void writeOnceParam(GunBytesUtil.GunWriteByteUtil util, Object parama) {
        if (parama instanceof Integer) {
            util.writeByte(RPCProtoclParamType.INT.val);
            util.write64((Integer) parama);
        } else if (parama instanceof String) {
            util.writeByte(RPCProtoclParamType.STRING.val);
            util.writeByte((byte) ((String) parama).length());
            util.write((String) parama);
        } else if (parama instanceof Boolean) {
            util.writeByte(RPCProtoclParamType.BOOLEAN.val);
            util.write((Boolean) parama);
        } else if (parama instanceof Byte) {
            util.writeByte(RPCProtoclParamType.BYTE.val);
            util.writeByte((Byte) parama);
        }

    }

    Object readOnceParam(GunBytesUtil.GunReadByteUtil util) {
        RPCProtoclParamType ptypei = RPCProtoclParamType.valuefrom(util.readByte());
        switch (ptypei) {
            case INT:
                return util.readInt64();
            case STRING:
                byte data = util.readByte();
                return new String(util.readByte(data));
            case BOOLEAN:
                return util.readBool();
            case BYTE:
                return util.readByte();
            default:
                break;
        }
        return null;

    }

    void publicSet(GunBytesUtil.GunWriteByteUtil util) {
        util.write(type.value);
        util.write(code.value);
    }


    boolean checkEnd(GunBytesUtil.GunReadByteUtil unserizutil) {
        byte[] end = unserizutil.readByte(2);
        return GunBytesUtil.compareBytesFromEnd(end, endFlage[0], endFlage[1]);
    }

    void publicUnSet(GunBytesUtil.GunReadByteUtil unserizutil) {
        this.type = RPCProtoclType.valuefrom(unserizutil.readInt());
        this.code = RPCProtoclCode.valuefrom(unserizutil.readInt());
    }

}