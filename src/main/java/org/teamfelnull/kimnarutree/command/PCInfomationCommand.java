package org.teamfelnull.kimnarutree.command;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Processor;

public class PCInfomationCommand {
	public static void register(CommandDispatcher<CommandSource> d) {
		d.register(Commands.literal("pcinfomation").requires((p_198359_0_) -> {
			return p_198359_0_.hasPermissionLevel(0);
		}).then(Commands.literal("processor").executes(source -> {
			return showPCInfo(source.getSource(), 0, 0);
		}).then(Commands.literal("name").executes(source -> {
			return showPCInfo(source.getSource(), 0, 0);
		}))
				.then(Commands.literal("family").executes(source -> {
					return showPCInfo(source.getSource(), 0, 1);
				}))));
	}

	private static int showPCInfo(CommandSource source, int hard, int name) {

		SystemInfo syminfo = new SystemInfo();
		HardwareAbstractionLayer hardware = syminfo.getHardware();

		if (hard == 0) {
			Processor[] cpus = hardware.getProcessors();

			if (name == 0) {
				for (Processor pro : cpus) {
					source.sendFeedback(
							new TranslationTextComponent("commands.pcinfomation.processor.name", pro.getName()), true);
					break;
				}

			} else if (name == 1) {
				for (Processor pro : cpus) {
					source.sendFeedback(
							new TranslationTextComponent("commands.pcinfomation.processor.identifier", pro.getIdentifier()), true);
					break;
				}
			}
		}

		return 1;
	}
}
