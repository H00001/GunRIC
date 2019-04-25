package netty.impl;

import netty.GunPilelineInterface;

abstract class BaseGunNettyWorker implements GunNettyWorkerInterface {
    final GunPilelineInterface pileline;

    BaseGunNettyWorker(final GunPilelineInterface pileline) {
        this.pileline = pileline;
    }
}
