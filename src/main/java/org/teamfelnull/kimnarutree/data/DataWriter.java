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
		/*
		if (KNTData.SAVED_PLAYER_DATA.containsKey(PlayerHelper.getUUID(pl)))
			KNTData.SAVED_PLAYER_DATA.get(PlayerHelper.getUUID(pl)).clear();

		if (KNTData.UNSAVED_PLAYER_DATA.containsKey(PlayerHelper.getUUID(pl)))
			KNTData.UNSAVED_PLAYER_DATA.get(PlayerHelper.getUUID(pl)).clear();
		*/
	}

	//ワールドがアンロードされた時
	public static void onWorldUnLoad(MinecraftServer ms) {
		//KNTData.SAVED_PLAYER_DATA.clear();
		//KNTData.UNSAVED_PLAYER_DATA.clear();
	}
}
