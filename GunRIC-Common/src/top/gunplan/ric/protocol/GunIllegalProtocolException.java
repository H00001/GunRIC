package top.gunplan.ric.protocol;

import top.gunplan.ric.protocol.exp.GunRicProtocolException;

import static top.gunplan.ric.protocol.exp.GunRicProtocolException.GunRicProtocolErrorType.PROROCOL_INVIDA;

/**
 * @author dosdrtt
 */
public class GunIllegalProtocolException extends GunRicProtocolException {

    private static final long serialVersionUID = 885590046532621322L;

    private GunRicAcceptProtocolTypes type = null;
    private Class<? extends AbstractGunRicProtocol> protocol = null;

    public <T extends AbstractGunRicProtocol> GunIllegalProtocolException(Class<T> protocol, GunRicAcceptProtocolTypes accept) {
        super(protocol.getName() + " is error", PROROCOL_INVIDA);
        this.protocol = protocol;
        this.type = accept;
    }

    @Override
    public String getMessage() {
        return protocol.getName() + " protocol is error, because " + type + " is :" + type.getType();
    }


    public enum GunRicAcceptProtocolTypes {

        /**
         * GunRicCenterAcceptProtocol
         * GunRicProviderAcceptProtocol
         */
        GunRicCenterAcceptProtocol("HELLO,REGISTER,GET"),
        GunRicProviderAcceptProtocol("HELLO,INPUT");
        private String type;

        GunRicAcceptProtocolTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }


}
