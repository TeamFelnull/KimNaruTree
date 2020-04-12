package org.teamfelnull.kimnarutree.command;

import java.util.Collection;

import org.teamfelnull.kimnarutree.util.MoneyUtil;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class MoneyCommand {

	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("money")
				.requires(source -> source.hasPermissionLevel(0))
				.executes(source -> {
					return showMoney(source.getSource(), null);
				})
				.then(Commands.argument("targets", EntityArgument.players())
						.executes(source -> {
							return showMoney(source.getSource(), EntityArgument.getPlayers(source, "targets"));
						})));
	}

	private static int showMoney(CommandSource source, Collection<ServerPlayerEntity> targets) {

		if (targets == null) {
			try {
				source.sendFeedback(new TranslationTextComponent("commands.money.show.me",
						MoneyUtil.getDisplayMoney(source.asPlayer())), true);
			} catch (CommandSyntaxException e) {

			}
		} else {

			for (ServerPlayerEntity pl : targets) {
				source.sendFeedback(new TranslationTextComponent("commands.money.show.player",
						pl.getName(), MoneyUtil.getDisplayMoney(pl)), true);
			}
		}
		return 1;
	}
}
