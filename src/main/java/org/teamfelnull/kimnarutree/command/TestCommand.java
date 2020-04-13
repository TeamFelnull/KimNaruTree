package org.teamfelnull.kimnarutree.command;

import org.teamfelnull.kimnarutree.data.BaseItemWorthData;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

public class TestCommand {

	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("test")
				.executes(source -> {

					for (Item item : ForgeRegistries.ITEMS) {
						source.getSource()
								.sendFeedback(new StringTextComponent(
										item.getRegistryName() + "=" + BaseItemWorthData.getData(item)), true);

					}
					return 1;
				}));
	}
}
