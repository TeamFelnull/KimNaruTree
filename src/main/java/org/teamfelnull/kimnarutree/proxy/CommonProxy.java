package org.teamfelnull.kimnarutree.proxy;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.advancements.KNTTriggers;
import org.teamfelnull.kimnarutree.advancements.TestTrigger;
import org.teamfelnull.kimnarutree.packet.PacketHandler;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;

public class CommonProxy {

	public void preInit() {
		KimNaruTree.LOGGER.info("Pre Initing...");
		PacketHandler.init();
		KNTTriggers.registerTriggers();
		CriteriaTriggers.register(TestTrigger.INSTANCE);
	}

	public void init() {
		KimNaruTree.LOGGER.info("Initing...");
	}

	public void posInit() {
		KimNaruTree.LOGGER.info("Post Initing...");

		System.out.println("traaaaaaa");

		for (ICriterionTrigger<?> s : CriteriaTriggers.getAll()) {
			System.out.println(s.getId());
		}

	}
}
