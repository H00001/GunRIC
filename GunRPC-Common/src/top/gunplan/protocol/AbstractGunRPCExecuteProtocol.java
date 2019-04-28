package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

public abstract class AbstractGunRPCExecuteProtocol extends AbstractGunRPCProtocl {

    int addLenByParam(int len, Object data) {
        final RPCProtoclParamType type = RPCProtoclParamType.valuefrom(data.getClass());
        if (type.stdlen != -1) {
            len += type.deslen + type.stdlen;
        } else {
            if (data instanceof String) {
                len += type.deslen + ((String) data).length();
            } else if (data instanceof int[]) {
                len += type.deslen + ((int[]) data).length * 4;
            } else {

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
            case LINT:
                byte ldata = util.readByte();
                int[] list = new int[ldata];
                for (int i = 0; i < ldata; i++) {
                    list[i] = util.readInt64();
                }
                help.obj = list;
            default:
                break;
        }
        return help;

    }

}
