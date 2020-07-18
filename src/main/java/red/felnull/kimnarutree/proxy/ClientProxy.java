package red.felnull.kimnarutree.proxy;


import net.minecraftforge.common.MinecraftForge;
import red.felnull.kimnarutree.client.gui.screen.KNTScrennContainerFactorys;
import red.felnull.kimnarutree.client.handler.ClientHandler;

public class ClientProxy extends CommonProxy {
    public static void clientInit() {
        KNTScrennContainerFactorys.registerFactories();
    }

    @Override
    public void preInit() {
        super.preInit();
        MinecraftForge.EVENT_BUS.register(ClientHandler.class);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void posInit() {
        super.posInit();
    }


}
