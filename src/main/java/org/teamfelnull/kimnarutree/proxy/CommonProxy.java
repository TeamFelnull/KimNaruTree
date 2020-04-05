package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;

public class CommonProxy {
	public void preInit() {
		KimNaruTree.LOGGER.info("Pre Initing...");
	}

	public void init() {
		KimNaruTree.LOGGER.info("Initing...");
	}

	public void posInit() {
		KimNaruTree.LOGGER.info("Post Initing...");
	}
}
