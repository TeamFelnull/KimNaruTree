package red.felnull.kimnarutree.proxy;


import net.minecraftforge.common.MinecraftForge;
import red.felnull.kimnarutree.client.gui.screen.KNTScreenContainerFactories;
import red.felnull.kimnarutree.client.handler.ClientHandler;
import red.felnull.kimnarutree.client.handler.RenderHandler;
import red.felnull.kimnarutree.client.render.entity.KNTEntityRendering;

public class ClientProxy extends CommonProxy {
    public static void clientInit() {
        KNTScreenContainerFactories.registerFactories();
        KNTEntityRendering.registerRendering();
    }

    @Override
    public void preInit() {
        super.preInit();
        MinecraftForge.EVENT_BUS.register(ClientHandler.class);
        MinecraftForge.EVENT_BUS.register(RenderHandler.class);
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
