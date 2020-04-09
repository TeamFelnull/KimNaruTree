package org.teamfelnull.kimnarutree.util;

import net.minecraft.client.Minecraft;

public class MCHelper {
	private static Minecraft mc = Minecraft.getInstance();

	public static int getFPS() {

		String d = mc.debug;

		return StringHelper.convertIntFromString((d.split("fps")[0]).split("/")[0]);
	}
}
