package org.teamfelnull.kimnarutree.util;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;

public class MoneyUtil {
	public static String getDisplayAmount(long money) {
		return money + I18n.format("money.currencyunit.g");
	}

	public static String getDisplayMony(String uuid) {
		return getDisplayAmount(PlayerDataLoader.getPlayerMoney(uuid));
	}

	public static String getDisplayMony(PlayerEntity pl) {
		return getDisplayAmount(PlayerDataLoader.getPlayerMoney(pl));
	}
}
