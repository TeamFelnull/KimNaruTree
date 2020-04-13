package org.teamfelnull.kimnarutree.util;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class PlayerHelper {

	public static String getUUID(MinecraftServer ms, String name) {
		return getUUID(ms.getPlayerList().getPlayerByUsername(name));
	}

	public static String getUUID(PlayerEntity pl) {
		return PlayerEntity.getUUID(pl.getGameProfile()).toString();
	}

	public static String getMCID(MinecraftServer ms, String name) {
		return getMCID(ms.getPlayerList().getPlayerByUsername(name));
	}

	public static String getMCID(PlayerEntity pl) {
		return pl.getName().getString();
	}

	public static Set<String> joinedUUID = new HashSet<>();
	public static Set<PlayerEntity> joinedPlayers = new HashSet<>();

	public static boolean hasJoined(PlayerEntity pl) {
		String uuid = PlayerHelper.getUUID(pl);
		if(joinedUUID.contains(uuid)) {
			return true;
		}
		joinedUUID.add(uuid);
		joinedPlayers.add(pl);
		return false;
	}

	public static void resetHistory() {
		joinedUUID = new HashSet<>();
		joinedPlayers = new HashSet<>();
	}
}
