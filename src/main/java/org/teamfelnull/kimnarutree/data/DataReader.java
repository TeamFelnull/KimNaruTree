package org.teamfelnull.kimnarutree.data;

import org.teamfelnull.kimnarutree.money.BankData;
import org.teamfelnull.kimnarutree.money.PlayerData;
import org.teamfelnull.kimnarutree.util.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class DataReader {

	//ワールド（内部サーバー）が開かれる時
	public static void onServerStarting(MinecraftServer ms) {
		BankData.read(ms);
		PlayerHelper.resetHistory();
		BaseItemWorthData.read();
		BaseItemWorthData.write();
		BaseItemWorthData.read();
	}

	//プレイヤーがログインした時
	public static void onPlayerLogin(PlayerEntity pl) {
		if(!PlayerHelper.hasJoined(pl)) {
			PlayerData.read(pl);
		}
		//SavedPlayerData.read(pl.getServer(), pl);
	}

	//ワールドがロードされた時
	public static void onWorldLoad(MinecraftServer ms) {

	}
}
