package top.gunplan.ric.protocol;

import top.gunplan.utils.GunBytesUtil;

/**
 * @author dosdrtt
 */
public class GunRicGetAddressProtocol extends AbstractCenterHelperProtocol implements GunRicOutputHelper {

    public GunRicGetAddressProtocol() {
        this.type = RicProtocolType.GET;
        this.code = RicProtocolCode.GET_REQ;
        //    this.setPort(0x0);
    }


    @Override
    public byte[] serialize() {
        GunBytesUtil.GunWriteByteStream util = createSpace();
        publicSet(util);
        stdHeadWrite(util);
        writeParamTypes(util);
        util.write(END_FLAG);
        return util.getInput();
    }

    @Override
    public GunBytesUtil.GunWriteByteStream createSpace() {
        int len = TYPE_LEN + CODE_LEN + SERIALNUM_LEN + 3 + interfaceName.length() + methodName.length() + paramcount + END_FLAG.length;
        return new GunBytesUtil.GunWriteByteStream(new byte[len]);
    }

    @Override
    public boolean unSerialize(GunBytesUtil.GunReadByteStream util) {
        publicUnSet(util);
        stdHeadAnaly(util);
        readParamType(util);
        return checkEnd(util) && checKNext(util);
    }
}