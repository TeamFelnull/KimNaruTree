package org.teamfelnull.kimnarutree.util;

import java.util.HashMap;
import java.util.Map;

import org.teamfelnull.kimnarutree.util.player.PlayerDatas;
import org.teamfelnull.kimnarutree.util.player.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class MoneyUtil {

	//金額表示
	public static TranslationTextComponent getDisplayAmount(long money) {
		return new TranslationTextComponent("money.currencyunit.g",money);
	}

	public static TranslationTextComponent getDisplayMoney(PlayerEntity pl) {
		return getDisplayAmount(getPlayerMoney(pl));
	}

	//残高取得
	public static long getPlayerMoney(PlayerEntity pl) {
		String uuid = PlayerHelper.getUUID(pl);
		if (!PlayerDatas.SAVED_PLAYER_DATA.containsKey(uuid)) {
			return 0;
		}
		Map<String, String> map = PlayerDatas.SAVED_PLAYER_DATA.get(uuid);
		long money = StringHelper.convertLongFromString(map.get("money"));
		return money;
	}

	//残高設定
	public static void setPlayerMoney(PlayerEntity pl, long money) {
		String uuid = PlayerHelper.getUUID(pl).toString();
		if (!PlayerDatas.SAVED_PLAYER_DATA.containsKey(uuid)) {
			PlayerDatas.SAVED_PLAYER_DATA.put(uuid, new HashMap<String, String>());
		}
		PlayerDatas.SAVED_PLAYER_DATA.get(uuid).put("money", StringHelper.convertStringFromLong(money));
		AdvancementUtil.checkWallet((ServerPlayerEntity) pl, money);
	}

	//残高追加
	public static void addPlayerMoney(PlayerEntity pl, long money) {
		String uuid = PlayerHelper.getUUID(pl).toString();
		if (!PlayerDatas.SAVED_PLAYER_DATA.containsKey(uuid)) {
			PlayerDatas.SAVED_PLAYER_DATA.put(uuid, new HashMap<String, String>());
		}
		PlayerDatas.SAVED_PLAYER_DATA.get(uuid).put("money", StringHelper.convertStringFromLong(getPlayerMoney(pl) + money));
		AdvancementUtil.checkWallet((ServerPlayerEntity) pl, money);
	}
}
