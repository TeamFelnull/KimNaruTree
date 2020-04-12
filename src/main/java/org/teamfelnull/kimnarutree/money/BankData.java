package org.teamfelnull.kimnarutree.money;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.util.FileLoadUtil;
import org.teamfelnull.kimnarutree.util.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.server.MinecraftServer;

public class BankData {

	private static long assets;//銀行の資産
	private static float risoku;//銀行に預けられたお金を増やすレート
	private static float risi;//銀行から借りられたお金を増やすレート
	private static Map<String, Long> deposit = new HashMap<>();//預金リスト<uuid, money>
	private static Map<String, Long> debt = new HashMap<>();//借金リスト<uuid, money>

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

	public static Map<String, Long> getDeposit() {
		return deposit;
	}

	public static Map<String, Long> getDebt() {
		return debt;
	}

	public static String getAll() {
		return getAssets() + " "+ getRisoku() + " "+ getRisi() + " "+ getDeposit().toString() + " "+ getDebt().toString();
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
		return (deposit.put(uuid, i) == null) ? i : i;
	}

	public static long setDebt(String uuid, long i) {
		return (debt.put(uuid, i) == null) ? i : i;
	}

	public static long setDeposit(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return setDeposit(uuid, i);
	}

	public static long setDebt(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return setDebt(uuid, i);
	}

	public static void setDeposit(Collection<ServerPlayerEntity> players, long i) {
		for (ServerPlayerEntity pl : players) {
			setDeposit(pl, i);
		}
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
		return (deposit.containsKey(uuid)) ? deposit.put(uuid, deposit.get(uuid) + i)
				: (deposit.put(uuid, i) == null) ? i : i;
	}

	public static long addDebt(String uuid, long i) {
		return (debt.containsKey(uuid)) ? debt.put(uuid, debt.get(uuid) + i)
				: (debt.put(uuid, i) == null) ? i : i;
	}

	public static long addDeposit(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return addDeposit(uuid, i);
	}

	public static long addDebt(PlayerEntity pl, long i) {
		String uuid = PlayerHelper.getUUID(pl);
		return addDebt(uuid, i);
	}

	public static void addDeposit(Collection<ServerPlayerEntity> players, long i) {
		for (ServerPlayerEntity pl : players) {
			addDeposit(pl, i);
		}
	}

	public static void addDebt(Collection<ServerPlayerEntity> players, long i) {
		for (ServerPlayerEntity pl : players) {
			addDebt(pl, i);
		}
	}



	//ファイル処理

	public static void read(MinecraftServer ms) {

		File file = new File(FileLoadUtil.getWorldSaveDataPath(ms) + "\\kimnarutree\\bankdata.dat");

		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				KimNaruTree.LOGGER.error("Could not create file ", e);
			}
		}

		try{
			FileInputStream fileinputstream = new FileInputStream(file);
			CompoundNBT nbt = CompressedStreamTools.readCompressed(fileinputstream);

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
        } catch (IOException ioexception) {
        	KimNaruTree.LOGGER.error("Could not load data ", ioexception);
        }
	}

	public static void write(MinecraftServer ms) {

		File file = new File(FileLoadUtil.getWorldSaveDataPath(ms) + "\\kimnarutree\\bankdata.dat");

		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				KimNaruTree.LOGGER.error("Could not create file ", e);
			}
		}

		CompoundNBT nbt = new CompoundNBT();

		nbt.putLong("assets", assets);
		nbt.putFloat("risoku", risoku);
		nbt.putFloat("risi", risi);
		for(String uuid : deposit.keySet()) {
			nbt.putLong("deposit_" + uuid, deposit.get(uuid));
		}
		for(String uuid : debt.keySet()) {
			nbt.putLong("debt_" + uuid, debt.get(uuid));
		}


        try (FileOutputStream fileoutputstream = new FileOutputStream(file)) {
            CompressedStreamTools.writeCompressed(nbt, fileoutputstream);
         } catch (IOException ioexception) {
        	 KimNaruTree.LOGGER.error("Could not save data ", ioexception);
         }
	}
}
