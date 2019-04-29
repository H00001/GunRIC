package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

import java.io.*;

public abstract class AbstractGunRPCExecuteProtocol extends AbstractGunRPCProtocl {

    int addLenByParam(int len, Object data) {
        final RPCProtoclParamType type = RPCProtoclParamType.valuefrom(data.getClass());
        if (type.stdlen != -1) {
            len += type.deslen + type.stdlen;
        } else {
            if (type == RPCProtoclParamType.STRING) {
                len += type.deslen + ((String) data).length();
            } else if (type == RPCProtoclParamType.LINT) {
                len += type.deslen + ((int[]) data).length * 4;
            } else {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(data);
                    len += bos.size() + type.deslen;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return len;
    }

    public static class ParamHelper {
        public Object obj;
        public Class<?> clazz;

        public ParamHelper(Object obj, Class<?> clazz) {
            this.obj = obj;
            this.clazz = clazz;
        }

        public ParamHelper() {

        }
    }

    ParamHelper readOnceParam(GunBytesUtil.GunReadByteUtil util) {
        RPCProtoclParamType ptypei = RPCProtoclParamType.valuefrom(util.readByte());
        ParamHelper help = new ParamHelper();
        help.clazz = ptypei.clazz;
        switch (ptypei) {
            case INT:
                help.obj = util.readInt64();
                break;
            case STRING:
                byte datal = util.readByte();
                help.obj = new String(util.readByte(datal));
                break;
            case BOOLEAN:
                help.obj = util.readBool();
                break;
            case BYTE:
                help.obj = util.readByte();
                break;
            case LINT:
                byte ldata = util.readByte();
                int[] list = new int[ldata];
                for (int i = 0; i < ldata; i++) {
                    list[i] = util.readInt64();
                }
                help.obj = list;
                break;
            case OBJECT:
                int datalen = util.readUByte();
                final byte[] objsav = util.readByte(datalen);
                try {
                    ObjectInputStream os = new ObjectInputStream(new ByteArrayInputStream(objsav));
                    help.obj = os.readObject();
                    help.clazz = help.obj.getClass();
                } catch (Exception e) {
                    help.obj = null;
                }
                break;
            default:
                break;
        }
        return help;

    }

}
