package red.felnull.kimnarutree;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import red.felnull.kimnarutree.proxy.ClientProxy;
import red.felnull.kimnarutree.proxy.CommonProxy;

@Mod(KimNaruTree.MOD_ID)
public class KimNaruTree {

    public static final String MOD_ID = "kimnarutree";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final CommonProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new CommonProxy());

    public KimNaruTree() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.preInit();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientProxy.clientInit();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        proxy.init();
    }

    private void processIMC(final InterModProcessEvent event) {
        proxy.posInit();
    }
}
