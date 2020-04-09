package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.client.handler.ClientHandler;
import org.teamfelnull.kimnarutree.client.render.entity.NPCRenderer;
import org.teamfelnull.kimnarutree.entity.KNTEntitys;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
	}

	public static void clientInit() {
		KimNaruTree.LOGGER.info("Client Initing...");
		MinecraftForge.EVENT_BUS.register(ClientHandler.class);
		RenderingRegistry.registerEntityRenderingHandler(KNTEntitys.NPC, NPCRenderer::new);
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
