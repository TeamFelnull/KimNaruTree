package org.teamfelnull.kimnarutree.command;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
=======
>>>>>>> master
import org.teamfelnull.kimnarutree.money.BankData;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
<<<<<<< HEAD
=======
>>>>>>> master
>>>>>>> master

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
=======
>>>>>>> master
>>>>>>> master

public class BankCommand {
	public static void register(CommandDispatcher<CommandSource> d) {

<<<<<<< HEAD
=======
<<<<<<< HEAD
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

=======
>>>>>>> master
		d.register(Commands.literal("bank")
			.requires((source) -> {return source.hasPermissionLevel(2);})
		.then(Commands.literal("read")
			.executes((source) -> {BankData.read(source.getSource().getServer());return 1;}))
		.then(Commands.literal("write")
			.executes((source) -> {BankData.write(source.getSource().getServer());return 1;}))
		.then(Commands.literal("set")
		.then((Commands.literal("assets"))
			.then(Commands.argument("arg", LongArgumentType.longArg())
				.executes((source) -> {BankData.setAssets(LongArgumentType.getLong(source, "arg"));return 1;})))
		.then((Commands.literal("risoku"))
			.then(Commands.argument("arg", FloatArgumentType.floatArg())
				.executes((source) -> {BankData.setRisoku(FloatArgumentType.getFloat(source, "arg"));return 1;})))
		.then((Commands.literal("risi"))
			.then(Commands.argument("arg", FloatArgumentType.floatArg())
				.executes((source) -> {BankData.setRisi(FloatArgumentType.getFloat(source, "arg"));return 1;})))
		.then((Commands.literal("deposit"))
		.then(Commands.argument("targets", EntityArgument.players())
			.then(Commands.argument("arg", LongArgumentType.longArg())
				.executes(source -> {BankData.setDeposit(EntityArgument.getPlayers(source, "targets"), LongArgumentType.getLong(source, "arg"));return 1;}))))
		.then((Commands.literal("debt"))
		.then(Commands.argument("targets", EntityArgument.players())
			.then(Commands.argument("arg", LongArgumentType.longArg())
				.executes(source -> {BankData.setDebt(EntityArgument.getPlayers(source, "targets"), LongArgumentType.getLong(source, "arg"));return 1;})))))
		.then(Commands.literal("add")
		.then((Commands.literal("assets"))
			.then(Commands.argument("arg", LongArgumentType.longArg())
				.executes((source) -> {BankData.addAssets(LongArgumentType.getLong(source, "arg"));return 1;})))
		.then((Commands.literal("risoku"))
			.then(Commands.argument("arg", FloatArgumentType.floatArg())
			.executes((source) -> {BankData.addRisoku(FloatArgumentType.getFloat(source, "arg"));return 1;})))
		.then((Commands.literal("risi"))
			.then(Commands.argument("arg", FloatArgumentType.floatArg())
				.executes((source) -> {BankData.addRisi(FloatArgumentType.getFloat(source, "arg"));return 1;})))
		.then((Commands.literal("deposit"))
		.then(Commands.argument("targets", EntityArgument.players())
			.then(Commands.argument("arg", LongArgumentType.longArg())
				.executes(source -> {BankData.addDeposit(EntityArgument.getPlayers(source, "targets"), LongArgumentType.getLong(source, "arg"));return 1;}))))
		.then((Commands.literal("debt"))
		.then(Commands.argument("targets", EntityArgument.players())
			.then(Commands.argument("arg", LongArgumentType.longArg())
				.executes(source -> {BankData.addDebt(EntityArgument.getPlayers(source, "targets"), LongArgumentType.getLong(source, "arg"));return 1;})))))
		);

	}
<<<<<<< HEAD
=======
>>>>>>> master
>>>>>>> master
}
