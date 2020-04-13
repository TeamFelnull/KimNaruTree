package org.teamfelnull.kimnarutree.money;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.teamfelnull.kimnarutree.util.FileInteractor;
import org.teamfelnull.kimnarutree.util.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;

public class BankData {

/*基本データのメンバ定義*/

	private static long assets;//銀行の資産
	private static float risoku;//銀行に預けられたお金を増やすレート
	private static float risi;//銀行から借りられたお金を増やすレート
	private static Map<String, Long> depositList = new HashMap<>();//預金リスト<uuid, money>
	private static Map<String, Long> debtList = new HashMap<>();//借金リスト<uuid, money>

	//getter
	public static long getAssets() {
		return assets;
	}

	public static float getRisoku() {
		return risoku;
	}

	public static float getRisi() {
		return risi;
	}

	public static long getDeposit(PlayerEntity pl) {
		String uuid = PlayerHelper.getUUID(pl);
		return depositList.containsKey(uuid) ? depositList.get(uuid) : 0;
	}

	public static long getDebt(PlayerEntity pl) {
		String uuid = PlayerHelper.getUUID(pl);
		return debtList.containsKey(uuid) ? debtList.get(uuid) : 0;
	}

	public static Map<String, Long> getDepositList() {
		return depositList;
	}

	public static Map<String, Long> getDebtList() {
		return debtList;
	}

	public static String getAll() {
		return "Assets:" +getAssets() + " Risoku:"+ getRisoku() + " Risi:"+ getRisi() + " DepositList:"+ getDepositList().toString() + " DebtList:"+ getDebtList().toString();
	}

	//setter
	public static long setAssets(long i) {
		return assets = i;
	}

	public static float setRisoku(float i) {
		return risoku = i;
	}

	public static float setRisi(float i) {
		return risi = i;
	}

	public static long setDeposit(String uuid, long i) {
		return depositList.put(uuid, i) == null ? i : i;
	}
	public static long setDeposit(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return setDeposit(uuid, i);
	}
	public static void setDeposit(Collection<ServerPlayerEntity> players, long i) {
		for (ServerPlayerEntity pl : players) {
			setDeposit(pl, i);
		}
	}

	public static long setDebt(String uuid, long i) {
		return debtList.put(uuid, i) == null ? i : i;
	}
	public static long setDebt(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return setDebt(uuid, i);
	}
	public static void setDebt(Collection<ServerPlayerEntity> players, long i) {
		for (ServerPlayerEntity pl : players) {
			setDebt(pl, i);
		}
	}

	//adder
	public static long addAssets(long i) {
		return assets += i;
	}

	public static float addRisoku(float i) {
		return risoku += i;
	}

	public static float addRisi(float i) {
		return risi += i;
	}

	public static long addDeposit(String uuid, long i) {
		return depositList.containsKey(uuid) ? depositList.put(uuid, depositList.get(uuid) + i) : setDeposit(uuid, i);
	}
	public static long addDeposit(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return addDeposit(uuid, i);
	}
	public static void addDeposit(Collection<ServerPlayerEntity> players, long i) {
		for (ServerPlayerEntity pl : players) {
			addDeposit(pl, i);
		}
	}

	public static long addDebt(String uuid, long i) {
		return debtList.containsKey(uuid) ? debtList.put(uuid, debtList.get(uuid) + i) : setDebt(uuid, i);
	}
	public static long addDebt(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return addDebt(uuid, i);
	}
	public static void addDebt(Collection<ServerPlayerEntity> players, long i) {
		for (ServerPlayerEntity pl : players) {
			addDebt(pl, i);
		}
	}



/*初期値を与える処理のメンバ定義*/

	private static boolean hasInitialized;
	private static long firstAssets = 10000000000L;
	private static float firstRisoku = 0.01F;
	private static float firstRisi = 0.01F;

	private static boolean hasInitialized() {
		return hasInitialized;
	}
	private static boolean setHasInitialized(boolean flag) {
		return hasInitialized = flag;
	}

	private static long initAssets() {
		return assets = firstAssets;
	}
	private static float initRisoku() {
		return risoku = firstRisoku;
	}
	private static float initRisi() {
		return risi = firstRisi;
	}



/*ファイル処理*/

	private static String extraPath = "\\kimnarutree\\bankdata.dat";

	public static void read(MinecraftServer ms) {

		//ファイルから読み取り
		CompoundNBT nbt = FileInteractor.newFile(ms, extraPath).read();

		//メモリに書き込み
		if(nbt.getBoolean("hasInitialized")) {
			setAssets(nbt.getLong("assets"));
			setRisoku(nbt.getFloat("risoku"));
			setRisi(nbt.getFloat("risi"));
			Set<String> keyset = nbt.keySet();
			for(String uuid : keyset) {
				String rawkey = uuid;
				if(uuid.contains("deposit_")) {
					setDeposit(uuid.replaceFirst("deposit_", ""), nbt.getLong(rawkey));
				} else if(uuid.contains("debt_")) {
					setDebt(uuid.replaceFirst("debt_", ""), nbt.getLong(rawkey));
				}
			}
		} else {
			initAssets();
			initRisoku();
			initRisi();
			setHasInitialized(true);
		}
	}

	public static void write(MinecraftServer ms) {

		//メモリから読み取り
		CompoundNBT nbt = new CompoundNBT();
		nbt.putBoolean("hasInitialized", hasInitialized());
		nbt.putLong("assets", assets);
		nbt.putFloat("risoku", risoku);
		nbt.putFloat("risi", risi);
		for(String uuid : depositList.keySet()) {
			nbt.putLong("deposit_" + uuid, depositList.get(uuid));
		}
		for(String uuid : debtList.keySet()) {
			nbt.putLong("debt_" + uuid, debtList.get(uuid));
		}

		//ファイルに書き込み
		FileInteractor.newFile(ms, extraPath).write(nbt);
	}
}
