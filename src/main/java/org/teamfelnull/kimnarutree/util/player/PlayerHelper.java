package org.teamfelnull.kimnarutree.util.player;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

public class PlayerHelper {

	public static String getUUID(MinecraftServer ms, String name) {
		return getUUID(ms.getPlayerList().getPlayerByUsername(name));
	}

	public static String getUUID(PlayerEntity pl) {
		return PlayerEntity.getUUID(pl.getGameProfile()).toString();
	}

	public static void setPlayerData(Map<String, Map<String, String>> map, String uuid, String statename,
			String state) {
		if (!map.containsKey(uuid)) {
			map.put(uuid, new HashMap<String, String>());
		}
		map.get(uuid).put(statename, state);
	}

	public static String getPlayerData(Map<String, Map<String, String>> map, String uuid, String statename) {
		if (!map.containsKey(uuid) || !map.get(uuid).containsKey(statename)) {
			return "";
		}
		return map.get(uuid).get(statename);
	}

	public static ServerPlayerEntity getServerPlayer(MinecraftServer ms, PlayerEntity pl) {

		return ms.getPlayerList().getPlayerByUsername(pl.getDisplayName().getString());
	}

	public static void grantAdvancement( ResourceLocation rl,ServerPlayerEntity spl) {

		Advancement advancement = spl.getServer().getAdvancementManager().getAdvancement(rl);
		AdvancementProgress advancementprogress = spl.getAdvancements().getProgress(advancement);

		if (advancementprogress.isDone())
			return;

		for (String s : advancementprogress.getRemaningCriteria()) {
			spl.getAdvancements().grantCriterion(advancement, s);
		}

	}
}
