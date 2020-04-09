package org.teamfelnull.kimnarutree.util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class MCHelper {

	public static int getFPS() {

		return ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, null, "debugFPS");
	}
}
