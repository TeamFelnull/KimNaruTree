package org.teamfelnull.kimnarutree.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.teamfelnull.kimnarutree.util.FileLoadUtil;
import org.teamfelnull.kimnarutree.util.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class SavedPlayerData {
	//UUID、ステータス名、ステータス、例:プレイヤーUUID、money,114514
	public static void setData(String uuid, String statename, String state) {
		if (!KNTDatas.SAVED_PLAYER_DATA.containsKey(uuid)) {
			KNTDatas.SAVED_PLAYER_DATA.put(uuid, new HashMap<String, String>());
		}
		KNTDatas.SAVED_PLAYER_DATA.get(uuid).put(statename, state);
	}

	//プレイヤーデーター取得
	public static String getData(String uuid, String statename) {
		if (!KNTDatas.SAVED_PLAYER_DATA.containsKey(uuid)) {
			return null;
		}
		Map<String, String> plmap = KNTDatas.SAVED_PLAYER_DATA.get(uuid);
		if (!plmap.containsKey(statename)) {
			return null;
		}
		return plmap.get(statename);
	}

	//特定のプレイヤーのデーターを読み込む
	public static void read(MinecraftServer ms, String uuid) {
		if (!KNTDatas.SAVED_PLAYER_DATA.containsKey(uuid)) {
			KNTDatas.SAVED_PLAYER_DATA.put(uuid, new HashMap<String, String>());
		}
		FileLoadUtil.txtMapReader(FileLoadUtil.getKNTPlayerDataPath(ms), uuid, KNTDatas.SAVED_PLAYER_DATA.get(uuid));
	}

	//特定のプレイヤーのデーターを読み込む
	public static void read(MinecraftServer ms, PlayerEntity pl) {
		read(ms, PlayerHelper.getUUID(pl));
	}

	//オンラインのプレイヤーのデーターを全て読み込む
	public static void read(MinecraftServer ms) {
		for (String name : ms.getOnlinePlayerNames()) {
			read(ms, ms.getPlayerList().getPlayerByUsername(name));
		}
	}

	//現在メモリにあるデータをすべて書き込む
	public static void write(MinecraftServer ms) {
		if (KNTDatas.SAVED_PLAYER_DATA.isEmpty()) {
			return;
		}
		for (String uuid : KNTDatas.SAVED_PLAYER_DATA.keySet()) {
			FileLoadUtil.txtMapWriter(FileLoadUtil.getKNTPlayerDataPath(ms), uuid,
					KNTDatas.SAVED_PLAYER_DATA.get(uuid),
					ms.getPlayerList().getPlayerByUUID(UUID.fromString(uuid)).getDisplayName().getString(), true);
		}
	}

	//オンラインのプレイヤーのデーターを全て別スレッドを使って読み込む
	public static void readerStart(MinecraftServer ms) {
		ReadWriteThread rwt = new ReadWriteThread(ms, false, null);
		rwt.start();
	}

	//特定のプレイヤーのデーターを別スレッドを使って読み込む
	public static void readerStart(MinecraftServer ms, PlayerEntity pl) {
		ReadWriteThread rwt = new ReadWriteThread(ms, false, pl);
		rwt.start();
	}

	//現在メモリにあるデータを全て別スレッドを使って書き込む
	public static void writerStart(MinecraftServer ms) {
		ReadWriteThread rwt = new ReadWriteThread(ms, true, null);
		rwt.start();
	}

	static class ReadWriteThread extends Thread {

		private MinecraftServer ms;
		private boolean write;
		private PlayerEntity pl;

		ReadWriteThread(MinecraftServer msIn, boolean writeIn, PlayerEntity playerIn) {
			this.ms = msIn;
			this.write = writeIn;
			this.pl = playerIn;
		}

		public void run() {
			if (this.write) {
				write(ms);
			} else {
				if (pl == null) {
					read(ms);
				} else {
					read(ms, pl);
				}
			}
		}
	}
}
