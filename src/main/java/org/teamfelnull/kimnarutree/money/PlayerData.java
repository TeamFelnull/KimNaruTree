package org.teamfelnull.kimnarutree.money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.teamfelnull.kimnarutree.util.FileInteractor;
import org.teamfelnull.kimnarutree.util.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;

public class PlayerData {

/*基本データのメンバ定義*/

	private static Map<String, String> mcidList = new HashMap<>();
	private static Map<String, Long> moneyList = new HashMap<>();

	//getter
	public static String getMCID(PlayerEntity pl) {
		String uuid = PlayerHelper.getUUID(pl);
		return mcidList.containsKey(uuid) ? mcidList.get(uuid) : initMCID(pl);
	}

	public static Long getMoney(PlayerEntity pl) {
		String uuid = PlayerHelper.getUUID(pl);
		return moneyList.containsKey(uuid) ? moneyList.get(uuid) : initMoney(pl);
	}

	public static String getAll(PlayerEntity pl) {
		return "hasInitialized:" + hasInitialized(pl) + " MCID:" + getMCID(pl) + " Money:" + getMoney(pl);
	}

	//setter
	public static String setMCID(PlayerEntity pl, String mcid) {
		String uuid = PlayerHelper.getUUID(pl);
		return mcidList.put(uuid, mcid) == null ? mcid : mcid;
	}

	public static long setMoney(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return moneyList.put(uuid, i) == null ? i : i;
	}

	//adder
	public static long addMoney(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return moneyList.put(uuid, moneyList.get(uuid) + i) == null ? i : i;
	}



/*初期値を与える処理のメンバ定義*/

	private static Map<String, Boolean> hasInitialized = new HashMap<>();
	private static long firstMoney = 1000L;

	private static boolean hasInitialized(PlayerEntity pl) {
		String uuid = PlayerHelper.getUUID(pl);
		return  hasInitialized.containsKey(uuid) ? hasInitialized.get(uuid) : true;
	}

	private static boolean setHasInitialized(PlayerEntity pl, boolean flag) {
		String uuid = PlayerHelper.getUUID(pl);
		return hasInitialized.put(uuid, flag) == null ? flag : flag;
	}

	private static String initMCID(PlayerEntity pl) {
		return setMCID(pl, PlayerHelper.getMCID(pl));
	}

	private static long initMoney(PlayerEntity pl) {
		return setMoney(pl, firstMoney);
	}



/*ファイル処理*/

	private static String extraPath = "\\kimnarutree\\playerdata\\";

	//全プレイヤーのデータをファイルのデータで上書き
	public static void readAll(MinecraftServer ms) {
		List<ServerPlayerEntity> playerList = ms.getPlayerList().getPlayers();
		for(ServerPlayerEntity pl : playerList) {
			read(pl);
		}
	}

	//一人のプレイヤーのデータをファイルのデータで上書き
	public static void read(PlayerEntity pl) {
		MinecraftServer ms = pl.getServer();
		String uuid = PlayerHelper.getUUID(pl);

		//ファイルから読み取り
		CompoundNBT nbt = FileInteractor.newFile(ms, extraPath + uuid + ".dat").read();

		//メモリに書き込み
		boolean b = nbt.getBoolean("hasInitialized");
		if(b) {
			setMCID(pl, nbt.getString("mcid"));
			setMoney(pl, nbt.getLong("money"));
		} else {
			initMCID(pl);
			initMoney(pl);
			setHasInitialized(pl, true);
		}
	}


	//全プレイヤーのデータをファイルに保存
	public static void writeAll() {
		for(PlayerEntity pl : PlayerHelper.joinedPlayers) {
			write(pl);
		}
	}

	//一人のプレイヤーのデータをファイルに保存
	public static void write(PlayerEntity pl) {
		MinecraftServer ms = pl.getServer();
		String uuid = pl.getUniqueID().toString();

		//メモリから読み取り
		CompoundNBT nbt = new CompoundNBT();
		nbt.putBoolean("hasInitialized", hasInitialized(pl));
		nbt.putString("mcid", getMCID(pl));
		nbt.putLong("money", getMoney(pl));

		//ファイルに書き込み
		FileInteractor.newFile(ms, extraPath + uuid + ".dat").write(nbt);
	}
}
