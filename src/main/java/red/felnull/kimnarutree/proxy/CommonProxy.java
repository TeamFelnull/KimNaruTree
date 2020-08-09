package red.felnull.kimnarutree.proxy;

import net.minecraftforge.common.MinecraftForge;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.data.KNTWorldData;
import red.felnull.kimnarutree.entity.KNTEntityTypes;
import red.felnull.kimnarutree.handler.ServerHandler;
import red.felnull.kimnarutree.packet.PacketHandler;
import red.felnull.otyacraftengine.api.registries.OERegistries;


public class CommonProxy {

    public void preInit() {
        PacketHandler.init();
        KNTWorldData.register();
        MinecraftForge.EVENT_BUS.register(ServerHandler.class);
        OERegistries.registrierModColor(KimNaruTree.MOD_ID, 16776960);
        KNTEntityTypes.registerAttributes();
        KNTDatas.init();
    }

    public void init() {

    }

    public void posInit() {

    }

}
