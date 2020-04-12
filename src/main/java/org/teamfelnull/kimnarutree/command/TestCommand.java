package org.teamfelnull.kimnarutree.command;

import org.teamfelnull.kimnarutree.util.FileLoadUtil;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class TestCommand {

	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("test")
				.executes(source -> {
					source.getSource()
							.sendFeedback(new StringTextComponent(FileLoadUtil.getOptionDataPath().toString()), true);
					return 1;
				}));
	}
}
