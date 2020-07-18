package red.felnull.kimnarutree.proxy;

import net.minecraftforge.common.MinecraftForge;
import red.felnull.kimnarutree.data.KNTWorldDatas;
import red.felnull.kimnarutree.handler.ServerHandler;

public class CommonProxy {

    public void preInit() {
        KNTWorldDatas.register();
        MinecraftForge.EVENT_BUS.register(ServerHandler.class);
    }

    public void init() {

    }

    public void posInit() {

    }

}
