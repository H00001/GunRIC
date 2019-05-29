package top.gunplan.ric.protocol;

import top.gunplan.netty.GunException;

/**
 * @author dosdrtt
 */
public class GunInvidaProtocolException extends GunException {

    private static final long serialVersionUID = 885590046532621322L;

    public GunInvidaProtocolException(String protocol) {
        this(protocol, "this");
    }

    public GunInvidaProtocolException(String protocol, String projectName) {
        super(protocol + " protocol is illegal in " + projectName + " project");
    }

}
