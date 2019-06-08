package top.gunplan.ric.protocol;

import top.gunplan.ric.protocol.exp.GunRicProtocolError;

import static top.gunplan.ric.protocol.exp.GunRicProtocolError.GunRicProtocolErrorType.PROROCOL_INVIDA;

/**
 * @author dosdrtt
 */
public class GunIllegalProtocolException extends GunRicProtocolError {

    private static final long serialVersionUID = 885590046532621322L;

    public GunIllegalProtocolException(String protocol) {
        this(protocol, "this");
    }

    public GunIllegalProtocolException(String protocol, String projectName) {
        super(protocol + " protocol is illegal in " + projectName + " project", PROROCOL_INVIDA);
    }

}
