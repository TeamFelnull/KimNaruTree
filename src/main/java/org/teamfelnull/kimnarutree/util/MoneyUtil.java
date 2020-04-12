package org.teamfelnull.kimnarutree.util;

import org.teamfelnull.kimnarutree.data.SavedPlayerData;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class MoneyUtil {

	//金額表示
	public static TranslationTextComponent getDisplayAmount(long money) {
		return new TranslationTextComponent("money.currencyunit.g", money);
	}

	//表示用金額表示
	public static TranslationTextComponent getDisplayMoney(ServerPlayerEntity pl) {
		return getDisplayAmount(getPlayerMoney(pl));
	}

	//残高取得
	public static long getPlayerMoney(ServerPlayerEntity pl) {
		String uuid = PlayerHelper.getUUID(pl);
		long money = StringHelper.convertLongFromString(SavedPlayerData.getData(uuid, "money"));
		return money;
	}

	//残高設定
	public static void setPlayerMoney(ServerPlayerEntity pl, long money) {
		String uuid = PlayerHelper.getUUID(pl).toString();
		SavedPlayerData.setData(uuid, "money", StringHelper.convertStringFromLong(money));
		AdvancementUtil.checkWallet(pl, money);
	}

	//残高追加
	public static void addPlayerMoney(ServerPlayerEntity pl, long money) {
		setPlayerMoney(pl, getPlayerMoney(pl) + money);
	}

}
