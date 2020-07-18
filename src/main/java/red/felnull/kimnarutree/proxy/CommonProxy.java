package red.felnull.kimnarutree.proxy;

import net.minecraftforge.common.MinecraftForge;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.KNTWorldDatas;
import red.felnull.kimnarutree.entity.KNTEntityTypes;
import red.felnull.kimnarutree.handler.ServerHandler;
import red.felnull.otyacraftengine.api.registries.OERegistries;

public class CommonProxy {

    public void preInit() {
        KNTWorldDatas.register();
        MinecraftForge.EVENT_BUS.register(ServerHandler.class);
        OERegistries.registrierModColor(KimNaruTree.MODID, 16776960);
        KNTEntityTypes.regsterAttrubytes();
    }

    public void init() {

    }

    public void posInit() {

    }

}
