package top.gunplan.ric.protocol;


import top.gunplan.netty.common.GunNettyContext;
import top.gunplan.utils.GunBytesUtil;

import java.io.*;
import java.lang.reflect.Method;


/**
 * AbstractGunRicExecuteProtocol
 *
 * @author dosdrtt
 */
public abstract class AbstractGunRicExecuteProtocol extends AbstractGunRicProtocol {
    String methodName;
    String interfaceName;
    public static byte METHOD_LEN = 1;
    public static byte INTERFACE_LEN = 1;
    public static byte METHOD_LEN_AND_INTERFACE_LEN = 1;


    public String methodName() {
        return methodName;
    }


    public void setInameMname(Method method) {
        this.setMethodName(method.getName());
        this.setInterfaceName(method.getDeclaringClass().getName());
    }


    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String interfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }


    int addLenByParam(int len, Object data) {
        final RicProtocolParamType type = RicProtocolParamType.valuefrom(data.getClass());
        if (type.stdlen != -1) {
            len += type.deslen + type.stdlen;
        } else {
            if (type == RicProtocolParamType.STRING) {
                len += type.deslen + ((String) data).length();
            } else if (type == RicProtocolParamType.LINT) {
                len += type.deslen + ((int[]) data).length * 4;
            } else if (type == RicProtocolParamType.LLINT) {
                len += type.deslen + ((int[][]) data).length * ((int[][]) data)[0].length * 4;
            } else {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    if (data instanceof Serializable) {
                        ObjectOutputStream oos = new ObjectOutputStream(bos);
                        oos.writeObject(data);
                        len += bos.size() + type.deslen;
                    } else {
                        throw new GunObjectCannotSerializableException(data.getClass().getName() + " can not been serializable");
                    }
                } catch (IOException e) {
                    GunNettyContext.logger.error(e);
                }

            }
        }
        return len;
    }

    void stdHeadAnaly(GunBytesUtil.GunReadByteStream util) {
        final int ilen = util.readByte();
        this.interfaceName = new String(util.readByte(ilen));
        int methodlen = util.readByte();
        this.methodName = new String(util.readByte(methodlen));
    }

    void stdHeadWrite(GunBytesUtil.GunWriteByteStream stream) {
        stream.writeByte((byte) interfaceName.length());
        stream.write(interfaceName);
        stream.writeByte((byte) methodName.length());
        stream.write(methodName);
    }

    public static class ParamHelper {
        public void setObj(Object obj) {
            this.clazz = obj.getClass();
            this.obj = obj;
        }

        public Object obj;
        Class<?> clazz;

        public Class<?> getClazz() {
            return clazz;
        }

        ParamHelper(Object obj, Class<?> clazz) {
            this.obj = obj;
            this.clazz = clazz;
        }

        public ParamHelper() {

        }
    }

    ParamHelper readOnceParam(GunBytesUtil.GunReadByteStream util) {
        RicProtocolParamType ptypei = RicProtocolParamType.valuefrom(util.readByte());
        ParamHelper help = new ParamHelper();
        help.clazz = ptypei.clazz;
        switch (ptypei) {
            case INT:
                help.obj = util.readInt32();
                break;
            case SHORT:
                help.obj = util.readInt();
                break;
            case LONG:
                help.obj = util.readLong();
                break;
            case STRING: {
                byte l = util.readByte();
                help.obj = new String(util.readByte(l));
            }
            break;
            case BOOLEAN:
                help.obj = util.readBool();
                break;
            case BYTE:
                help.obj = util.readByte();
                break;
            case LINT: {
                byte l = util.readByte();
                int[] list = new int[l];
                for (int i = 0; i < l; i++) {
                    list[i] = util.readInt32();
                }
                help.obj = list;
            }
            break;
            case LLINT: {
                byte l = util.readByte();
                byte l1 = util.readByte();
                int[][] list = new int[l][l1];
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < l1; j++) {
                        list[i][j] = util.readInt32();
                    }
                }
                help.obj = list;
            }
            break;
            case OBJECT: {
                int l = util.readInt();
                final byte[] o = util.readByte(l);
                try {
                    ObjectInputStream os = new ObjectInputStream(new ByteArrayInputStream(o));
                    help.obj = os.readObject();
                    help.clazz = help.obj.getClass();
                } catch (Exception e) {
                    help.obj = null;
                }
            }
            break;
            default:
                break;
        }
        return help;
    }


    /**
     * unSerialize
     *
     * @param util GunReadByteStream stream util
     * @return result succeed:true , fail:false
     */
    @Override
    public abstract boolean unSerialize(GunBytesUtil.GunReadByteStream util);


}