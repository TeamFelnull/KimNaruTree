package org.teamfelnull.kimnarutree.command;

import java.util.Collection;

import org.teamfelnull.kimnarutree.money.PlayerData;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class PlayerDataCommand {

	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("playerdata")
			.requires((source) -> {return source.hasPermissionLevel(2);})
			.executes(source -> {return playerInfo(source.getSource(), null);})
			.then(Commands.argument("targets", EntityArgument.players())
				.executes(source -> {return playerInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"));})
				.then(Commands.literal("read")
					.executes(source -> {return readData(source.getSource(), EntityArgument.getPlayers(source, "targets"));})
				)
				.then(Commands.literal("write")
					.executes(source -> {return writeData(source.getSource(), EntityArgument.getPlayers(source, "targets"));})
				)
			)
		);
	}

	private static int playerInfo(CommandSource source, Collection<ServerPlayerEntity> collection) {
		if (collection == null) {
			try {
				source.sendFeedback(new TranslationTextComponent(PlayerData.getAll(source.asPlayer())), true);
			} catch (CommandSyntaxException e) {
				return 0;
			}
		} else {
			for (ServerPlayerEntity pl : collection) {
				source.sendFeedback(new TranslationTextComponent(PlayerData.getAll(pl)), true);
			}
		}
		return 1;
	}

	private static int readData(CommandSource source, Collection<ServerPlayerEntity> collection) {
		source.sendFeedback(new TranslationTextComponent("commands.playerdata.read"), true);
		for (PlayerEntity pl : collection) {
			PlayerData.read(pl);
		}
		return 1;
	}

	private static int writeData(CommandSource source, Collection<ServerPlayerEntity> collection) {
		source.sendFeedback(new TranslationTextComponent("commands.playerdata.write"), true);
		for (PlayerEntity pl : collection) {
			PlayerData.write(pl);
		}
		return 1;
	}
}
