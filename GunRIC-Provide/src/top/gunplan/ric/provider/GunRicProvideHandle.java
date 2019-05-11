package top.gunplan.ric.provider;


import top.gunplan.netty.protocol.GunNetOutputInterface;
import top.gunplan.protocol.*;
import top.gunplan.ric.protocol.GunCombineOutput;
import top.gunplan.ric.protocol.GunRicInputProtocol;


/**
 * @author dosdrtt
 * @concurrent
 * @date 1518000000
 */
public class GunRicProvideHandle extends AbstractGunRicBaseProviderHandle {


    private GunStdRicHandle handle = new GunStdRicHandle();

    @Override
    public GunNetOutputInterface dealEvent(GunRicInputProtocol protocol) {
        if (protocol.getNext() == null) {
            return handle.dealDataEvent(protocol);
        } else {
            GunCombineOutput capt = new GunCombineOutput();
            for (; protocol != null; ) {
                capt.push(handle.dealDataEvent(protocol));
                protocol = (GunRicInputProtocol) protocol.getNext();
            }
            return capt;
        }
    }

}
