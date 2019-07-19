package top.gunplan.ric.stand;

import top.gunplan.netty.protocol.GunNetInputInterface;
import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.ric.protocol.GunRicNxInput;
import top.gunplan.ric.protocol.RicProtocolCode;
import top.gunplan.ric.protocol.RicProtocolType;

/**
 * GunRicBaseStand
 *
 * @author frank albert
 * @version 0.0.0.1
 * @date 2019-07-18 23:42
 */
public interface GunRicBaseStand extends GunNetInputInterface, GunNetOutputInterface, GunRicNxInput {
    /**
     * get  type
     *
     * @return type
     */
    RicProtocolType type();


    /**
     * get code
     *
     * @return code
     */
    RicProtocolCode code();


    void setCode(RicProtocolCode coder);


    void setType(RicProtocolType typer);

    /**
     * @return serialNumber
     */
    int serialNumber();


    GunRicBaseStand next();


    void next(GunRicBaseStand next);

    void setSerialnumber(int number);
}
