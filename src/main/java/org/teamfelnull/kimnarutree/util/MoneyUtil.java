package org.teamfelnull.kimnarutree.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class MoneyUtil {
	public static TranslationTextComponent getDisplayAmount(long money) {

		return new TranslationTextComponent("money.currencyunit.g",money);
	}

	public static TranslationTextComponent getDisplayMony(String uuid) {
		return getDisplayAmount(PlayerDataLoader.getPlayerMoney(uuid));
	}

	public static TranslationTextComponent getDisplayMony(PlayerEntity pl) {
		return getDisplayAmount(PlayerDataLoader.getPlayerMoney(pl));
	}
}
