package top.gunplan.protocol;


import top.gunplan.utils.GunBytesUtil;

public abstract class AbstractGunRPCExecuteProtocol extends AbstractGunRPCProtocl {

    int addLenByParam(int len, Object data) {
        final RPCProtoclParamType type = RPCProtoclParamType.valuefrom(data.getClass());
        if (type.stdlen != -1) {
            len += type.deslen + type.stdlen;
        } else {
            len += type.deslen + ((String) data).length();
        }
        return len;
    }

    public static class ParamHelper {
        public Object obj;
        public Class<?> clazz;
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
                byte data = util.readByte();
                help.obj = new String(util.readByte(data));
                break;
            case BOOLEAN:
                help.obj = util.readBool();
                break;
            case BYTE:
                help.obj = util.readByte();
            default:
                break;
        }
        return help;

    }

}
