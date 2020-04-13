package org.teamfelnull.kimnarutree.util;

import org.teamfelnull.kimnarutree.money.PlayerData;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class MoneyUtil {

	//金額表示
	public static TranslationTextComponent getDisplayAmount(long money) {
		return new TranslationTextComponent("money.currencyunit.g", money);
	}

	//表示用金額表示
	public static TranslationTextComponent getDisplayMoney(ServerPlayerEntity pl) {
		return getDisplayAmount(PlayerData.getMoney(pl));
	}
}
