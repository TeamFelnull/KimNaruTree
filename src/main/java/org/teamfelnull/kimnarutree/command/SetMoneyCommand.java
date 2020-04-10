package org.teamfelnull.kimnarutree.command;

import java.util.Collection;

import org.teamfelnull.kimnarutree.util.MoneyUtil;
import org.teamfelnull.kimnarutree.util.StringHelper;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class SetMoneyCommand {

	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("setmoney")
			.requires((p_198359_0_) -> {return p_198359_0_.hasPermissionLevel(2);})
		.then(Commands.argument("targets", EntityArgument.players())
			.executes(source -> {return setMoney(source.getSource(), EntityArgument.getPlayers(source, "targets"), "");})
		.then(Commands.argument("balance", StringArgumentType.string())
			.executes(source -> {return setMoney(source.getSource(), EntityArgument.getPlayers(source, "targets"),StringArgumentType.getString(source, "balance"));})
		)));
	}

	private static int setMoney(CommandSource source, Collection<ServerPlayerEntity> collection, String i) {

		int ic = 0;
		long mo = StringHelper.convertLongFromString(i);

		if (collection == null || mo == 0) {
			source.sendFeedback(new TranslationTextComponent("commands.money.set.f"), true);
			return 0;
		}

		for (ServerPlayerEntity pl : collection) {
			source.sendFeedback(new TranslationTextComponent("commands.money.set", pl.getName(), MoneyUtil.getDisplayMoney(pl),MoneyUtil.getDisplayAmount(mo)),true);
			MoneyUtil.setPlayerMoney(pl, mo);
		}

		return ic;
	}
}
