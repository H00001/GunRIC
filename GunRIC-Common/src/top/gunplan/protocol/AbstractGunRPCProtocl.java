package top.gunplan.protocol;


import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.protocol.exp.GunRPCException;
import top.gunplan.utils.GunBytesUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;


/**
 * @author dosdrtt
 * @version 0.0.0.0
 * @date
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

    public final static byte TYPE_LEN = 2;
    public final static byte CODE_LEN = 2;


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

    public final static byte[] END_FLAGE = {0x7a, 0x7a};


    static class Helper {
        private final GunBytesUtil.GunWriteByteUtil util;

        Helper(GunBytesUtil.GunWriteByteUtil util) {
            this.util = util;
        }

        void write(Object obj) {
            String name = obj.getClass().getSimpleName();
            RPCProtoclParamType type = RPCProtoclParamType.valuefrom(obj.getClass());
            Method md;
            try {
                if (name.contains("[]")) {
                    name = name.replace("[]", "");
                    name = "L" + name;
                    if (obj instanceof int[][]) {
                        name = "L" + name;
                    }
                }
                md = this.getClass().getDeclaredMethod("write" + name, obj.getClass());
                util.writeByte(type.val);
                md.invoke(this, obj);
            } catch (Exception e) {
                util.writeByte(type.val);
                if (writeObject0(obj) == -1) {
                    throw new GunRPCException("write Object Fail");
                }
            }
        }

        private void writeInteger(Integer parama) {
            util.write32(parama);
        }


        private void writeShort(Short parama) {
            util.write(parama);
        }


        private void writeLong(Long parama) {
            util.writeLong(parama);
        }


        private void writeLint(int[] list) {
            util.writeByte((byte) list.length);
            for (int val : list) {
                util.write32(val);
            }
        }

        private void writeLLint(int[][] list) {
            util.writeByte((byte) list.length);
            util.writeByte((byte) list[0].length);
            for (int[] var0 : list) {
                for (int val1 : var0) {
                    util.write32(val1);
                }
            }
        }

        private void writeString(String string) {
            util.writeByte((byte) string.length());
            util.write(string);
        }

        private void writeBoolean(Boolean bool) {
            util.write(bool);
        }

        private void writeByte(Byte b) {

            util.writeByte(b);
        }

        private int writeObject0(Object b) {

            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
                ObjectOutputStream os = new ObjectOutputStream(bos);
                os.writeObject(b);
                util.writeByte((byte) bos.size());
                util.write(bos.toByteArray());
                return 0;
            } catch (IOException e) {
                //  throw new GunRPCException("object write error");
                return -1;
            }
        }
    }

    void writeOnceParam(GunBytesUtil.GunWriteByteUtil util, Object parama) {
        Helper help = new Helper(util);
        help.write(parama);

    }


    void publicSet(GunBytesUtil.GunWriteByteUtil util) {
        util.write(type.value);
        util.write(code.value);
    }


    boolean checkEnd(GunBytesUtil.GunReadByteUtil unserizutil) {
        byte[] end = unserizutil.readByte(END_FLAGE.length);
        return GunBytesUtil.compareBytesFromEnd(end, END_FLAGE);
    }

    void publicUnSet(GunBytesUtil.GunReadByteUtil unserizutil) {
        this.type = RPCProtoclType.valuefrom(unserizutil.readInt());
        this.code = RPCProtoclCode.valuefrom(unserizutil.readInt());
    }

}