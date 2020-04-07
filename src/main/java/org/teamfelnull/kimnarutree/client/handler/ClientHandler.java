package org.teamfelnull.kimnarutree.client.handler;

import org.teamfelnull.kimnarutree.packet.MessageSendSysmtemInfo;
import org.teamfelnull.kimnarutree.packet.PacketHandler;

import com.mojang.blaze3d.platform.GLX;

import mezz.jei.events.PlayerJoinedWorldEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

public class ClientHandler {
	private static boolean frust;
	public static String gupname;
	public static Minecraft mc = Minecraft.getInstance();

	@SubscribeEvent
	public static void onTick(TickEvent.ClientTickEvent e) {

		if (!frust)
			gupname = GLX.getRenderer();

		frust = true;

		if (mc.player == null)
			return;

		long i = Runtime.getRuntime().maxMemory();
		long j = Runtime.getRuntime().totalMemory();
		long k = Runtime.getRuntime().freeMemory();
		long l = j - k;

		String memory = String.format("% 2d%% %03d/%03dMB", l * 100L / i, bytesToMb(l), bytesToMb(i));

		@SuppressWarnings({ "resource", "static-access" })
		String fps = Minecraft.getDebugFPS() + "fps / " + mc.getInstance().gameSettings.framerateLimit + "fps";

		PacketHandler.INSTANCE.sendToServer(new MessageSendSysmtemInfo("", "", "", "", memory, fps, true));

	}

	private static long bytesToMb(long bytes) {
		return bytes / 1024L / 1024L;
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerJoinedWorldEvent e) {

		SystemInfo syminfo = new SystemInfo();
		//	HardwareAbstractionLayer hardware = syminfo.getHardware();
		OperatingSystem oss = syminfo.getOperatingSystem();

		String java = "Java " + System.getProperty("java.version") + " " + (mc.isJava64bit() ? "64bit" : "32bit");
		String os = oss.getManufacturer() + " " + System.getProperty("os.name") + " "
				+ System.getProperty("os.version") + " " + System.getProperty("os.arch");
		String cpu = GLX.getCpuInfo();
		String gpu = gupname;

		PacketHandler.INSTANCE.sendToServer(new MessageSendSysmtemInfo(java, os, cpu, gpu, "", "", false));

	}

}
