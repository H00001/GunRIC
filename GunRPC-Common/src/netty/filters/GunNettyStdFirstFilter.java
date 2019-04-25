package netty.filters;

import netty.GunNettyFilter;
import netty.GunFunctionMappingInterFace;
import netty.anno.GunNetFilterOrder;
import netty.common.GunNettyPropertyManager;
import netty.impl.CunCoreDataEventLoop;
import netty.impl.GunInputFilterChecker;
import netty.impl.GunOutputFilterChecker;
import netty.impl.propertys.GunCoreProperty;
import top.gunplan.utils.AbstractGunBaseLogUtil;
import top.gunplan.utils.GunBytesUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * GunNettyStdFirstFilter First Input Filter and Last Output Filter
 * this class is high dangerous
 *
 * @author dosdrtt
 */
@GunNetFilterOrder(index = 0)
public class GunNettyStdFirstFilter implements GunNettyFilter {

    private void dealCloseEvent(SelectionKey key) throws IOException {
        AbstractGunBaseLogUtil.debug("Client closed", "[CONNECTION]");
        key.channel().close();
        key.cancel();
    }

    @Override
    public DealResult doInputFilter(GunInputFilterChecker filterDto) throws Exception {

        byte[] readbata;
        SelectionKey key = filterDto.getKey();
        if (key.isValid()) {
            try {
                GunFunctionMappingInterFace<SocketChannel, byte[]> reader = GunBytesUtil::readFromChannel;
                readbata = reader.readBytes((SocketChannel) key.channel());
                filterDto.setSrc(readbata);
            } catch (IOException e) {
                dealCloseEvent(key);
                e.printStackTrace();
                return DealResult.CLOSE;
            }
            if (readbata == null) {
                dealCloseEvent(key);
                return DealResult.CLOSE;
            } else {
                if (GunNettyPropertyManager.getCore().getConnection() == GunCoreProperty.connectionType.CLOSE) {
                    filterDto.getKey().cancel();
                } else if (GunNettyPropertyManager.getCore().getConnection() == GunCoreProperty.connectionType.KEEP_ALIVE) {
                    key.interestOps(SelectionKey.OP_READ);
                    ((CunCoreDataEventLoop) key.attachment()).incrAndContinueLoop();

                }
                return DealResult.NEXT;

            }
        } else {
            return DealResult.NOTDEALALLNEXT;
        }
    }

    @Override
    public DealResult doOutputFilter(GunOutputFilterChecker filterDto) throws IOException {
        SocketChannel channel = (SocketChannel) filterDto.getKey().channel();
        channel.write(ByteBuffer.wrap(filterDto.getRespobj().serialize()));
        //

        if (GunNettyPropertyManager.getCore().getConnection() == GunCoreProperty.connectionType.CLOSE) {
            filterDto.getKey().channel().close();
            AbstractGunBaseLogUtil.debug("close initiative");
        }
        //
        return DealResult.NEXT;
    }
}
