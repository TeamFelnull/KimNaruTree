package org.teamfelnull.kimnarutree.util;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class AdvancementUtil {

	public static void grantAdvancement(ResourceLocation rl, ServerPlayerEntity spl) {

		Advancement advancement = spl.getServer().getAdvancementManager().getAdvancement(rl);
		AdvancementProgress advancementprogress = spl.getAdvancements().getProgress(advancement);

		if (advancementprogress.isDone())
			return;

		for (String s : advancementprogress.getRemaningCriteria()) {
			spl.getAdvancements().grantCriterion(advancement, s);
		}
	}

	public static void checkWallet(ServerPlayerEntity player, long money) {
		if (money >= 1000) {
			grantAdvancement(new ResourceLocation("kimnarutree:root"), player);
		}
		if (money >= 10000) {
			grantAdvancement(new ResourceLocation("kimnarutree:wallet_10000"), player);
		}
		if (money >= 1000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:wallet_1000000"), player);
		}
		if (money >= 10000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:wallet_10000000"), player);
		}
		if (money >= 50000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:wallet_50000000"), player);
		}
		if (money >= 100000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:wallet_100000000"), player);
		}
		if (money >= 500000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:wallet_500000000"), player);
		}
		if (money >= 1000000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:wallet_1000000000"), player);
		}
		if (money >= 10000000000L) {
			grantAdvancement(new ResourceLocation("kimnarutree:wallet_10000000000"), player);
		}
	}

	public static void checkFuneralCost(ServerPlayerEntity player, long cost) {
		if (cost >= 0) {
			grantAdvancement(new ResourceLocation("kimnarutree:funeral_0"), player);
		}
		if (cost >= 500) {
			grantAdvancement(new ResourceLocation("kimnarutree:funeral_500"), player);
		}
		if (cost >= 1000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:funeral_1000000"), player);
		}
		if (cost >= 30000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:funeral_30000000"), player);
		}
		if (cost >= 1000000000) {
			grantAdvancement(new ResourceLocation("kimnarutree:funeral_1000000000"), player);
		}
	}
}