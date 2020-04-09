package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.advancements.KNTTriggers;
import org.teamfelnull.kimnarutree.packet.PacketHandler;

public class CommonProxy {

	public void preInit() {
		KimNaruTree.LOGGER.info("Pre Initing...");
		PacketHandler.init();
		KNTTriggers.registerTriggers();
	}

	public void init() {
		KimNaruTree.LOGGER.info("Initing...");
	}

	public void posInit() {
		KimNaruTree.LOGGER.info("Post Initing...");
	}
}
