package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit() {
		super.preInit();
	}

	public static void clientInit() {
		KimNaruTree.LOGGER.info("Client Initing...");
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
