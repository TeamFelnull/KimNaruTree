package org.teamfelnull.kimnarutree.command;

import org.teamfelnull.kimnarutree.money.BankData;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;

public class BankDataCommand {
	public static void register(CommandDispatcher<CommandSource> d) {

		d.register(Commands.literal("bank")
			.requires((source) -> {return source.hasPermissionLevel(2);})
			.then(Commands.literal("read")
				.executes((source) -> {BankData.read(source.getSource().getServer());return 1;})
			)
			.then(Commands.literal("write")
				.executes((source) -> {BankData.write(source.getSource().getServer());return 1;})
			)
			.then(Commands.literal("set")
				.then((Commands.literal("assets"))
					.then(Commands.argument("arg", LongArgumentType.longArg())
						.executes((source) -> {BankData.setAssets(LongArgumentType.getLong(source, "arg"));return 1;})
					)
				)
				.then((Commands.literal("risoku"))
					.then(Commands.argument("arg", FloatArgumentType.floatArg())
						.executes((source) -> {BankData.setRisoku(FloatArgumentType.getFloat(source, "arg"));return 1;})
					)
				)
				.then((Commands.literal("risi"))
					.then(Commands.argument("arg", FloatArgumentType.floatArg())
						.executes((source) -> {BankData.setRisi(FloatArgumentType.getFloat(source, "arg"));return 1;})
					)
				)
				.then((Commands.literal("deposit"))
					.then(Commands.argument("targets", EntityArgument.players())
						.then(Commands.argument("arg", LongArgumentType.longArg())
							.executes(source -> {BankData.setDeposit(EntityArgument.getPlayers(source, "targets"), LongArgumentType.getLong(source, "arg"));return 1;})
						)
					)
				)
				.then((Commands.literal("debt"))
					.then(Commands.argument("targets", EntityArgument.players())
						.then(Commands.argument("arg", LongArgumentType.longArg())
							.executes(source -> {BankData.setDebt(EntityArgument.getPlayers(source, "targets"), LongArgumentType.getLong(source, "arg"));return 1;})
						)
					)
				)
			)
			.then(Commands.literal("add")
				.then((Commands.literal("assets"))
					.then(Commands.argument("arg", LongArgumentType.longArg())
						.executes((source) -> {BankData.addAssets(LongArgumentType.getLong(source, "arg"));return 1;})
					)
				)
				.then((Commands.literal("risoku"))
					.then(Commands.argument("arg", FloatArgumentType.floatArg())
						.executes((source) -> {BankData.addRisoku(FloatArgumentType.getFloat(source, "arg"));return 1;})
					)
				)
				.then((Commands.literal("risi"))
					.then(Commands.argument("arg", FloatArgumentType.floatArg())
						.executes((source) -> {BankData.addRisi(FloatArgumentType.getFloat(source, "arg"));return 1;})
					)
				)
				.then((Commands.literal("deposit"))
					.then(Commands.argument("targets", EntityArgument.players())
						.then(Commands.argument("arg", LongArgumentType.longArg())
							.executes(source -> {BankData.addDeposit(EntityArgument.getPlayers(source, "targets"), LongArgumentType.getLong(source, "arg"));return 1;})
						)
					)
				)
				.then((Commands.literal("debt"))
					.then(Commands.argument("targets", EntityArgument.players())
						.then(Commands.argument("arg", LongArgumentType.longArg())
							.executes(source -> {BankData.addDebt(EntityArgument.getPlayers(source, "targets"), LongArgumentType.getLong(source, "arg"));return 1;})
						)
					)
				)
			)
		);

	}
}
