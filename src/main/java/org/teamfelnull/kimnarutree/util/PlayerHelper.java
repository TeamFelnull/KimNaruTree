package org.teamfelnull.kimnarutree.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class PlayerHelper {

	public static String getUUID(MinecraftServer ms, String name) {
		return getUUID(ms.getPlayerList().getPlayerByUsername(name));
	}

	public static String getUUID(PlayerEntity pl) {
		return PlayerEntity.getUUID(pl.getGameProfile()).toString();
	}
}
