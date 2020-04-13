package org.teamfelnull.kimnarutree.data;

import org.teamfelnull.kimnarutree.money.BankData;
import org.teamfelnull.kimnarutree.money.PlayerData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class DataWriter {

	//ワールドが保存される時
	public static void onWorldSave(MinecraftServer ms) {
		BankData.write(ms);
		PlayerData.writeAll();
		//SavedPlayerData.writerStart(ms);
	}

	//プレイヤーがログアウトした時
	public static void onPlayerLogout(PlayerEntity pl) {

	}

	//ワールド（内部サーバー）が閉じた時
	public static void serverStopping(MinecraftServer ms) {

	}
}
