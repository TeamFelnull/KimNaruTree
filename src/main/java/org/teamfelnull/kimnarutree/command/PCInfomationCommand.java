package org.teamfelnull.kimnarutree.command;

import java.util.Collection;

import org.teamfelnull.kimnarutree.data.UnSavedPlayerData;
import org.teamfelnull.kimnarutree.util.PlayerHelper;
import org.teamfelnull.kimnarutree.util.StringHelper;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class PCInfomationCommand {
	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("pcinfomation")
			.requires((p_198359_0_) -> {return p_198359_0_.hasPermissionLevel(0);})
			.then(Commands.argument("targets", EntityArgument.players())
				.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 0);})
				.then(Commands.literal("all")
						.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 0);})
				)
				.then(Commands.literal("java")
					.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 1);})
				)
				.then(Commands.literal("os")
					.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 2);})
				)
				.then(Commands.literal("processor")
					.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 3);})
				)
				.then(Commands.literal("graphiccard")
					.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 4);})
				)
				.then(Commands.literal("memory")
					.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 5);})
				)
				.then(Commands.literal("fps")
					.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 6);})
				)
				.then(Commands.literal("mod")
					.executes(source -> {return showPCInfo(source.getSource(), EntityArgument.getPlayers(source, "targets"), 7);})
				)
			)
		);
	}

	private static int showPCInfo(CommandSource source, Collection<ServerPlayerEntity> collection, int num) {
		for (ServerPlayerEntity pl : collection) {
			switch (num) {
				case 1:
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.java", pl.getName(),
								getPlayerPCInfo(pl, "info.java")),
						true);
				case 2:
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.os", pl.getName(),
								getPlayerPCInfo(pl, "info.os")),
						true);
				case 3:
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.processor", pl.getName(),
								getPlayerPCInfo(pl, "info.cpu")),
						true);
				case 4:
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.graphiccard", pl.getName(),
								getPlayerPCInfo(pl, "info.gpu")),
						true);
				case 5:
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.memory", pl.getName(),
								getPlayerPCInfo(pl, "info.memory")),
						true);
				case 6:
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.fps", pl.getName(),
								getPlayerPCInfo(pl, "info.fps")),
						true);
				case 7:
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.mod", pl.getName(),
								getPlayerPCInfo(pl, "info.mod")),
						true);
				case 0:
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.java", pl.getName(),
								getPlayerPCInfo(pl, "info.java")),
						true);
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.os", pl.getName(),
								getPlayerPCInfo(pl, "info.os")),
						true);
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.processor", pl.getName(),
								getPlayerPCInfo(pl, "info.cpu")),
						true);
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.graphiccard", pl.getName(),
								getPlayerPCInfo(pl, "info.gpu")),
					true);
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.memory", pl.getName(),
								getPlayerPCInfo(pl, "info.memory")),
						true);
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.fps", pl.getName(),
								getPlayerPCInfo(pl, "info.fps")),
						true);
					source.sendFeedback(
						new TranslationTextComponent("commands.pcinfomation.mod", pl.getName(),
								getPlayerPCInfo(pl, "info.mod")),
						true);
			}
		}
		if (collection.size() >= 2) {
			if (num == 6) {
				int fpsavrage = 0;
				for (ServerPlayerEntity pls : collection) {
					int fps = StringHelper.convertIntFromString(getPlayerPCInfo(pls, "info.fps").split("/")[0].replace("fps", ""));
					fpsavrage += fps;
				}
				fpsavrage /= collection.size();
				source.sendFeedback(new TranslationTextComponent("commands.pcinfomation.fps.average", fpsavrage), true);
			}
		}
		return 1;
	}

	private static String getPlayerPCInfo(PlayerEntity pl, String statename) {
		return UnSavedPlayerData.getData(PlayerHelper.getUUID(pl), statename);
	}
}
