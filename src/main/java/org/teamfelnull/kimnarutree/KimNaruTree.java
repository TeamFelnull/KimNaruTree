package org.teamfelnull.kimnarutree;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.teamfelnull.kimnarutree.handler.ServerHandler;
import org.teamfelnull.kimnarutree.proxy.ClientProxy;
import org.teamfelnull.kimnarutree.proxy.CommonProxy;
import org.teamfelnull.kimnarutree.proxy.ServerProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("kimnarutree")
public class KimNaruTree {
	public static String MODID = "kimnarutree";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final CommonProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(),
			() -> () -> new ServerProxy());

	public KimNaruTree() {

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		MinecraftForge.EVENT_BUS.register(ServerHandler.class);
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
