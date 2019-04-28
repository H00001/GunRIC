package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;



import java.lang.reflect.Method;


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


    static class Helper {
        private final GunBytesUtil.GunWriteByteUtil util;

        public Helper(GunBytesUtil.GunWriteByteUtil util) {
            this.util = util;
        }

        void write(Object obj) {
            String name = obj.getClass().getSimpleName();
            Method md;
            try {
                if (name.contains("[]")) {
                    name = name.replace("[]", "");
                    name = "L" + name;
                }
                md = this.getClass().getDeclaredMethod("write" + name, obj.getClass());
                md.invoke(this, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void writeInteger(Integer parama) {
            util.writeByte(RPCProtoclParamType.INT.val);
            util.write64(parama);
        }

        private void writeLint(int[] list) {
            util.writeByte(RPCProtoclParamType.LINT.val);
            util.writeByte((byte) list.length);
            for (int val : list) {
                util.write64(val);
            }
        }

        private void writeString(String string) {
            util.writeByte(RPCProtoclParamType.STRING.val);
            util.writeByte((byte) string.length());
            util.write(string);
        }

        private void writeBoolean(Boolean bool) {
            util.writeByte(RPCProtoclParamType.BOOLEAN.val);
            util.write(bool);
        }

        private void writeByte(Byte b) {
            util.writeByte(RPCProtoclParamType.BYTE.val);
            util.writeByte(b);
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
        byte[] end = unserizutil.readByte(2);
        return GunBytesUtil.compareBytesFromEnd(end, endFlage[0], endFlage[1]);
    }

    void publicUnSet(GunBytesUtil.GunReadByteUtil unserizutil) {
        this.type = RPCProtoclType.valuefrom(unserizutil.readInt());
        this.code = RPCProtoclCode.valuefrom(unserizutil.readInt());
    }

}