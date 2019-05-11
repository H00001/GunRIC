package top.gunplan.ric.protocol;


import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.exp.GunRPCException;
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
public abstract class AbstractGunRicProtocol implements GunDubboNxInput, GunNetInputInterface, GunNetOutputInterface {


    boolean checKNext(GunBytesUtil.GunReadByteStream util) {
        boolean thTrueSeria = true;
        if (util.getLenSum() - util.getNowflag() > 3) {
            AbstractGunRicProtocol protocol = GunRicTypeDividePacketManage.findPackage(util);
            thTrueSeria = protocol.unSerialize(util);
            setNext(protocol);
        }
        return thTrueSeria;
    }

    @Override
    public boolean unSerialize(byte[] in) {
        GunBytesUtil.GunReadByteStream util = new GunBytesUtil.GunReadByteStream(in);
        return unSerialize(util);
    }

    RicProtocolType type;
    RicProtocolCode code;
    /**
     * chain style to divide pacjet
     */
    private AbstractGunRicProtocol next;
    private int serialnumber = 0;

    public AbstractGunRicProtocol getNext() {
        return next;
    }

    private void setNext(AbstractGunRicProtocol next) {
        this.next = next;
    }

    public int getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(int serialnumber) {
        this.serialnumber = serialnumber;
    }

    final static byte TYPE_LEN = 2;
    final static byte CODE_LEN = 2;
    final static byte SERIALNUM_LEN = 2;


    public RicProtocolType getType() {
        return type;
    }

    public void setType(RicProtocolType type) {
        this.type = type;
    }

    public RicProtocolCode getCode() {
        return code;
    }

    public void setCode(RicProtocolCode code) {
        this.code = code;
    }

    public final static byte[] END_FLAG = {0x7a, 0x7a};

    void writeOnceParam(GunBytesUtil.GunWriteByteStream util, Object parama) {
        Helper help = new Helper(util);
        help.write(parama);

    }

    void publicSet(GunBytesUtil.GunWriteByteStream util) {
        util.write(type.value);
        util.write(code.value);
        util.write(serialnumber);
    }

    boolean checkEnd(GunBytesUtil.GunReadByteStream unserizutil) {
        byte[] end = unserizutil.readByte(END_FLAG.length);
        return GunBytesUtil.compareBytesFromEnd(end, END_FLAG);
    }

    void publicUnSet(GunBytesUtil.GunReadByteStream unserizutil) {
        this.type = RicProtocolType.valuefrom(unserizutil.readInt());
        this.code = RicProtocolCode.valuefrom(unserizutil.readInt());
        this.serialnumber = unserizutil.readInt();
    }

    static class Helper {
        private final GunBytesUtil.GunWriteByteStream util;

        Helper(GunBytesUtil.GunWriteByteStream util) {
            this.util = util;
        }

        void write(Object obj) {
            String name = obj.getClass().getSimpleName();
            RicProtocolParamType type = RicProtocolParamType.valuefrom(obj.getClass());
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

        /**
         * reflect execute
         *
         * @param parama write param
         */
        private void writeInteger(Integer parama) {
            util.write32(parama);
        }

        /**
         * reflect execute
         *
         * @param parama write param
         */
        private void writeShort(Short parama) {
            util.write(parama);
        }


        /**
         * reflect execute
         *
         * @param parama write param
         */
        private void writeLong(Long parama) {
            util.writeLong(parama);
        }


        /**
         * reflect execute
         *
         * @param list write param
         */
        private void writeLint(int[] list) {
            util.writeByte((byte) list.length);
            for (int val : list) {
                util.write32(val);
            }
        }


        /**
         * reflect execute
         *
         * @param list write param
         */
        private void writeLLint(int[][] list) {
            util.writeByte((byte) list.length);
            util.writeByte((byte) list[0].length);
            for (int[] var0 : list) {
                for (int val1 : var0) {
                    util.write32(val1);
                }
            }
        }


        /**
         * reflect execute
         *
         * @param string write param
         */
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
                util.write(bos.size());
                util.write(bos.toByteArray());
                return 0;
            } catch (IOException e) {
                //  throw new GunRPCException("object write error");
                return -1;
            }
        }
    }


}