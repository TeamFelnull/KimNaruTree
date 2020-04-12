package org.teamfelnull.kimnarutree.data;

import org.teamfelnull.kimnarutree.money.BankData;
import org.teamfelnull.kimnarutree.util.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class DataLoadSave {
	//ワールド（内部サーバー）が開かられる時
	public static void serverStarting(MinecraftServer ms) {
		BankData.read(ms);
		SavedPlayerData.readerStart(ms);
	}

	//プレイヤーがログインした時
	public static void playerLogin(PlayerEntity pl) {
		SavedPlayerData.read(pl.getServer(), pl);
	}

	//プレイヤーがログアウトした時
	public static void playerLogout(PlayerEntity pl) {
		if (KNTDatas.SAVED_PLAYER_DATA.containsKey(PlayerHelper.getUUID(pl)))
			KNTDatas.SAVED_PLAYER_DATA.get(PlayerHelper.getUUID(pl)).clear();

		if (KNTDatas.UNSAVED_PLAYER_DATA.containsKey(PlayerHelper.getUUID(pl)))
			KNTDatas.UNSAVED_PLAYER_DATA.get(PlayerHelper.getUUID(pl)).clear();
	}

	//ワールドが保存される時
	public static void worldSave(MinecraftServer ms) {
		SavedPlayerData.writerStart(ms);
		BankData.write(ms);
	}

	//ワールドがアンロードされた時
	public static void worldUnLoad(MinecraftServer ms) {
		KNTDatas.SAVED_PLAYER_DATA.clear();
		KNTDatas.UNSAVED_PLAYER_DATA.clear();
	}

}
