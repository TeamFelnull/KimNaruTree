package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.client.handler.ClientHandler;

import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit() {
		super.preInit();

	}

	public static void clientInit() {
		KimNaruTree.LOGGER.info("Client Initing...");
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
