package org.teamfelnull.kimnarutree.command;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;

public class KNTCommands {
	public static void registerCommand(CommandDispatcher<CommandSource> d) {
		MoneyCommand.register(d);
		ReloadMoneyCommand.register(d);
		SetMoneyCommand.register(d);
		TestCommand.register(d);
	//	PCInfomationCommand.register(d);
	}
}
