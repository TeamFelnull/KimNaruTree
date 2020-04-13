package org.teamfelnull.kimnarutree.command;

import org.teamfelnull.kimnarutree.util.FileHelper;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class TestCommand {

	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("test")
			.executes(source ->
				{
					source.getSource().sendFeedback(new StringTextComponent(FileHelper.getOptionDataPath().toString()), true);return 1;
				}
			)
		);
	}
}
