package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.packet.PacketHandler;
import org.teamfelnull.kimnarutree.registries.DefaltBaseItemWorthRegistrey;

public class CommonProxy {

	public void preInit() {
		KimNaruTree.LOGGER.info("Pre Initing...");
		PacketHandler.init();
	}

	public void init() {
		KimNaruTree.LOGGER.info("Initing...");
		DefaltBaseItemWorthRegistrey.init();
	}

	public void posInit() {
		KimNaruTree.LOGGER.info("Post Initing...");

	}
}
