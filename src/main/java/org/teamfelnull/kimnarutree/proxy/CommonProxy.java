package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.packet.PacketHandler;

public class CommonProxy {
	public void preInit() {
		KimNaruTree.LOGGER.info("Pre Initing...");
		PacketHandler.init();
	}

	public void init() {
		KimNaruTree.LOGGER.info("Initing...");
		//PlayerDataLoader.setPlayerData("test", "money", "810");
	}

	public void posInit() {
		KimNaruTree.LOGGER.info("Post Initing...");
	}
}
