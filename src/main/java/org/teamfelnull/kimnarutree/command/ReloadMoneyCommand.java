package org.teamfelnull.kimnarutree.command;

import org.teamfelnull.kimnarutree.data.SavedPlayerData;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class ReloadMoneyCommand {

	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("reloadmoney").requires((p_198359_0_) -> {
			return p_198359_0_.hasPermissionLevel(2);
		}).then(Commands.literal("read").executes(source -> {
			return readMoney(source.getSource(), false);
		}).then(Commands.argument("thread", BoolArgumentType.bool()).executes((p_198356_0_) -> {
			return readMoney(p_198356_0_.getSource(), BoolArgumentType.getBool(p_198356_0_, "thread"));
		}))).then(Commands.literal("write").executes(source -> {
			return writeMoney(source.getSource(), false);
		}).then(Commands.argument("thread", BoolArgumentType.bool()).executes((p_198356_0_) -> {
			return writeMoney(p_198356_0_.getSource(), BoolArgumentType.getBool(p_198356_0_, "thread"));
		}))));
	}

	private static int readMoney(CommandSource source, boolean thread) {
		source.sendFeedback(new TranslationTextComponent("commands.money.read"), true);
		if (thread)
			SavedPlayerData.readerStart(source.getServer());
		else
			SavedPlayerData.read(source.getServer());

		return 1;
	}

	private static int writeMoney(CommandSource source, boolean thread) {
		source.sendFeedback(new TranslationTextComponent("commands.money.write"), true);
		if (thread)
			SavedPlayerData.writerStart(source.getServer());
		else
			SavedPlayerData.write(source.getServer());

		return 1;
	}

}
