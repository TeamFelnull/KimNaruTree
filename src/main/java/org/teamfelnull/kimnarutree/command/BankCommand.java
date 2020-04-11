package org.teamfelnull.kimnarutree.command;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class BankCommand {
	public static void register(CommandDispatcher<CommandSource> d) {

		d.register(Commands.literal("bank").requires((source) -> {
			return source.hasPermissionLevel(2);
		}).then(Commands.literal("read").executes(source -> {
			return syori(source.getSource(), "read", null, null);
		}).then((Commands.literal("assets")).executes((source) -> {
			return syori(source.getSource(), "read", "assets", null);
		})).then((Commands.literal("risoku")).executes((source) -> {
			return syori(source.getSource(), "read", "risoku", null);
		})).then((Commands.literal("risi")).executes((source) -> {
			return syori(source.getSource(), "read", "risi", null);
		})).then((Commands.literal("deposit")).executes((source) -> {
			return syori(source.getSource(), "read", "deposit", null);
		}).then(Commands.argument("targets", EntityArgument.players()).executes(source -> {
			return syori(source.getSource(), "read", "deposit", EntityArgument.getPlayers(source, "targets"));
		}))).then((Commands.literal("debt")).executes((source) -> {
			return syori(source.getSource(), "read", "debt", null);
		}).then(Commands.argument("targets", EntityArgument.players()).executes(source -> {
			return syori(source.getSource(), "read", "debt", EntityArgument.getPlayers(source, "targets"));
		})))).then(Commands.literal("write").executes(source -> {
			return syori(source.getSource(), "write", null, null);
		}).then((Commands.literal("assets")).executes((source) -> {
			return syori(source.getSource(), "write", "assets", null);
		})).then((Commands.literal("risoku")).executes((source) -> {
			return syori(source.getSource(), "write", "risoku", null);
		})).then((Commands.literal("risi")).executes((source) -> {
			return syori(source.getSource(), "write", "risi", null);
		})).then((Commands.literal("deposit")).executes((source) -> {
			return syori(source.getSource(), "write", "deposit", null);
		}).then(Commands.argument("targets", EntityArgument.players()).executes(source -> {
			return syori(source.getSource(), "write", "deposit", EntityArgument.getPlayers(source, "targets"));
		}))).then((Commands.literal("debt")).executes((source) -> {
			return syori(source.getSource(), "write", "debt", null);
		}).then(Commands.argument("targets", EntityArgument.players()).executes(source -> {
			return syori(source.getSource(), "write", "debt", EntityArgument.getPlayers(source, "targets"));
		})))));

	}

	private static int syori(CommandSource source, String st1, String st2, Collection<ServerPlayerEntity> collection) {
		source.sendFeedback(new StringTextComponent("st1=" + st1 + " st2=" + st2), true);
		return 1;
	}

}
