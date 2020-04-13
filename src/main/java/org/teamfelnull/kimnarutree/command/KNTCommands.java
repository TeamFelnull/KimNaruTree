package org.teamfelnull.kimnarutree.command;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;

public class KNTCommands {

	public static void registerCommand(CommandDispatcher<CommandSource> d) {
		TestCommand.register(d);
		MoneyCommand.register(d);
		PlayerDataCommand.register(d);
		BankDataCommand.register(d);
		PCInfomationCommand.register(d);
	}
}
