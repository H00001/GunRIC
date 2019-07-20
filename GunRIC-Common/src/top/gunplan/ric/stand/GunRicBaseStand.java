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
public interface GunRicBaseStand extends GunNetInputInterface, GunNetOutputInterface, GunRicNxInput, Serialer, GunLinked<GunRicBaseStand> {
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

    @Override
    GunRicBaseStand next();


    @Override
    void next(GunRicBaseStand next);


}
