package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;

public class ServerProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		KimNaruTree.side = 2;
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
