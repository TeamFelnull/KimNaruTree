package org.teamfelnull.kimnarutree.util.player;

import java.util.HashMap;
import java.util.UUID;

import org.teamfelnull.kimnarutree.util.FileLoadUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class PlayerDataLoader {

	public static void setPlayerData(String plname, String statename, String state) {
		if (!PlayerDatas.SAVED_PLAYER_DATA.containsKey(plname)) {
			PlayerDatas.SAVED_PLAYER_DATA.put(plname, new HashMap<String, String>());
		}
		PlayerDatas.SAVED_PLAYER_DATA.get(plname).put(statename, state);
	}

	public static void readOnlyPlayer(PlayerEntity pl) {
		readOnlyPlayer(pl.getServer(), pl.getDisplayName().getString());
	}

	public static void readOnlyPlayer(MinecraftServer ms, String name) {

		String uuid = PlayerHelper.getUUID(ms, name).toString();

		if (!PlayerDatas.SAVED_PLAYER_DATA.containsKey(uuid)) {
			PlayerDatas.SAVED_PLAYER_DATA.put(uuid, new HashMap<String, String>());
		}

		FileLoadUtil.txtMapReader(FileLoadUtil.getKNTPlayerDataPath(ms), uuid,PlayerDatas.SAVED_PLAYER_DATA.get(uuid));
	}

	public static void read(MinecraftServer ms) {
		for (String name : ms.getOnlinePlayerNames()) {
			readOnlyPlayer(ms, name);
		}
	}

	public static void write(MinecraftServer ms) {
		if (PlayerDatas.SAVED_PLAYER_DATA.isEmpty()) {
			return;
		}
		for (String uuid : PlayerDatas.SAVED_PLAYER_DATA.keySet()) {
			FileLoadUtil.txtMapWriter(FileLoadUtil.getKNTPlayerDataPath(ms), uuid,
				PlayerDatas.SAVED_PLAYER_DATA.get(uuid),
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
