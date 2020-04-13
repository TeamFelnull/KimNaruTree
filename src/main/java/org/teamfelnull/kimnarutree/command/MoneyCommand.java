package org.teamfelnull.kimnarutree.command;

import java.util.Collection;

import org.teamfelnull.kimnarutree.money.PlayerData;
import org.teamfelnull.kimnarutree.util.MoneyUtil;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class MoneyCommand {

	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("money")
			.requires((source) -> {return source.hasPermissionLevel(2);})
			.executes(source -> {return showMoney(source.getSource(), null);})
			.then(Commands.argument("targets", EntityArgument.players())
				.executes(source -> {return showMoney(source.getSource(), EntityArgument.getPlayers(source, "targets"));})
				.then(Commands.literal("add")
					.then(Commands.argument("arg", LongArgumentType.longArg())
						.executes(source -> {return addMoney(source.getSource(), EntityArgument.getPlayers(source, "targets"),LongArgumentType.getLong(source, "arg"));})
					)
				)
				.then(Commands.literal("set")
					.then(Commands.argument("arg", LongArgumentType.longArg())
						.executes(source -> {return setMoney(source.getSource(), EntityArgument.getPlayers(source, "targets"),LongArgumentType.getLong(source, "arg"));})
					)
				)
			)
		);
	}

	private static int showMoney(CommandSource source, Collection<ServerPlayerEntity> collection) {
		if (collection == null) {
			try {
				source.sendFeedback(new TranslationTextComponent("commands.money.show.me", MoneyUtil.getDisplayMoney(source.asPlayer())), true);
			} catch (CommandSyntaxException e) {
				return 0;
			}
		} else {
			for (ServerPlayerEntity pl : collection) {
				source.sendFeedback(new TranslationTextComponent("commands.money.show.player", pl.getName(), MoneyUtil.getDisplayMoney(pl)), true);
			}
		}
		return 1;
	}

	private static int addMoney(CommandSource source, Collection<ServerPlayerEntity> collection, long i) {
		for (ServerPlayerEntity pl : collection) {
			source.sendFeedback(new TranslationTextComponent("commands.money.add", MoneyUtil.getDisplayAmount(i), pl.getName()),true);
			PlayerData.addMoney(pl, i);
		}
		return 1;
	}

	private static int setMoney(CommandSource source, Collection<ServerPlayerEntity> collection, long i) {
		for (ServerPlayerEntity pl : collection) {
			source.sendFeedback(new TranslationTextComponent("commands.money.set", pl.getName(), MoneyUtil.getDisplayMoney(pl),MoneyUtil.getDisplayAmount(i)),true);
			PlayerData.setMoney(pl, i);
		}
		return 1;
	}
}
