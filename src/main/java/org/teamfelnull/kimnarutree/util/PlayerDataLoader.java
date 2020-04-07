package org.teamfelnull.kimnarutree.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class PlayerDataLoader {
	public static Map<String, Map<String, String>> PLAYER_DATA = new HashMap<String, Map<String, String>>();

	public static void setPlayerData(String plname, String statename, String state) {
		if (!PLAYER_DATA.containsKey(plname))
			PLAYER_DATA.put(plname, new HashMap<String, String>());

		PLAYER_DATA.get(plname).put(statename, state);

	}

	public static void read(MinecraftServer ms) {
		for (String name : ms.getOnlinePlayerNames()) {
			String uuid = PlayerHelper.getUUID(ms, name).toString();

			if (!PLAYER_DATA.containsKey(uuid))
				PLAYER_DATA.put(uuid, new HashMap<String, String>());

			FileLoadUtil.txtMapReader(FileLoadUtil.getKNTPlayerDataPath(ms), uuid, PLAYER_DATA.get(uuid));

		}
	}

	public static void setPlayerMoney(String uuid, long money) {
		if (!PLAYER_DATA.containsKey(uuid))
			PLAYER_DATA.put(uuid, new HashMap<String, String>());

		PLAYER_DATA.get(uuid).put("money", StringHelper.convertStringFromLong(money));

	}

	public static void setPlayerMoney(PlayerEntity pl, long money) {
		setPlayerMoney(PlayerHelper.getUUID(pl).toString(), money);
	}

	public static long getPlayerMoney(PlayerEntity pl) {

		return getPlayerMoney(PlayerHelper.getUUID(pl));
	}

	public static long getPlayerMoney(String uuid) {

		if (!PLAYER_DATA.containsKey(uuid))
			return 0;

		Map<String, String> map = PLAYER_DATA.get(uuid);

		return StringHelper.convertLongFromString(map.get("money"));
	}

	public static void write(MinecraftServer ms) {

		if (PLAYER_DATA.isEmpty())
			return;

		for (String uuid : PLAYER_DATA.keySet()) {
			FileLoadUtil.txtMapWriter(FileLoadUtil.getKNTPlayerDataPath(ms), uuid, PLAYER_DATA.get(uuid),
					ms.getPlayerList().getPlayerByUUID(UUID.fromString(uuid)).getDisplayName().getString(), true);
		}

	}

	public static void load(MinecraftServer ms) {
		read(ms);
		write(ms);
	}

	public static void loadStart(MinecraftServer ms) {
		LoadThread rt = new LoadThread(ms);
		rt.start();
	}

	public static void readerStart(MinecraftServer ms) {
		ReadThread rt = new ReadThread(ms);
		rt.start();
	}

	public static void writerStart(MinecraftServer ms) {
		WriteThread wt = new WriteThread(ms);
		wt.start();
	}

	static class WriteThread extends Thread {
		private MinecraftServer MCS;

		WriteThread(MinecraftServer ms) {
			this.MCS = ms;
		}

		public void run() {
			write(MCS);
		}
	}

	static class ReadThread extends Thread {
		private MinecraftServer MCS;

		ReadThread(MinecraftServer ms) {
			this.MCS = ms;
		}

		public void run() {
			read(MCS);
		}
	}

	static class LoadThread extends Thread {
		private MinecraftServer MCS;

		LoadThread(MinecraftServer ms) {
			this.MCS = ms;
		}

		public void run() {
			load(MCS);
		}
	}
}
