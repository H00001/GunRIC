package top.gunplan.protocol;

import java.util.Stack;

abstract class AbstractGunRPCExecuteProtocol extends AbstractGunRPCProtocl {

    int addLenByParam(int len, Object data) {
        final RPCProtoclParamType type = RPCProtoclParamType.valuefrom(data.getClass());
        if (type.stdlen != -1) {
            len += type.deslen + type.stdlen;
        } else {
            len += type.deslen + ((String) data).length();
        }
        return len;
    }



}
