package org.teamfelnull.kimnarutree.client.handler;

import org.teamfelnull.kimnarutree.item.PassbookItem;
import org.teamfelnull.kimnarutree.packet.MessageSendSysmtemInfo;
import org.teamfelnull.kimnarutree.packet.PacketHandler;
import org.teamfelnull.kimnarutree.util.MCHelper;
import org.teamfelnull.kimnarutree.util.ModUtil;

import com.mojang.blaze3d.platform.PlatformDescriptors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

public class ClientHandler {

	private static boolean frist;
	public static String gupname;
	public static Minecraft mc = Minecraft.getInstance();

	@SubscribeEvent
	public static void onTick(TickEvent.ClientTickEvent e) {

		if (!frist) {
			gupname = PlatformDescriptors.func_227776_c_();
		}
		frist = true;

		if (mc.player == null) {
			return;
		}

		long i = Runtime.getRuntime().maxMemory();
		long j = Runtime.getRuntime().totalMemory();
		long k = Runtime.getRuntime().freeMemory();
		long l = j - k;

		String memory = String.format("% 2d%% %03d/%03dMB", l * 100L / i, bytesToMb(l), bytesToMb(i));
		@SuppressWarnings({ "resource", "static-access" })
		String fps = MCHelper.getFPS() + "fps / " + mc.getInstance().gameSettings.framerateLimit + "fps";
		PacketHandler.INSTANCE.sendToServer(new MessageSendSysmtemInfo("", "", "", "", "", memory, fps, true));
	}

	private static long bytesToMb(long bytes) {
		return bytes / 1024L / 1024L;
	}

	@SubscribeEvent
	@SuppressWarnings("resource")
	public static void onPlayerJoin(EntityJoinWorldEvent e) {

		if (!e.getWorld().isRemote || !(e.getEntity() instanceof ClientPlayerEntity)) {
			return;
		}

		SystemInfo syminfo = new SystemInfo();
		OperatingSystem oss = syminfo.getOperatingSystem();
		String java = "Java " + System.getProperty("java.version") + " " + (mc.isJava64bit() ? "64bit" : "32bit");
		String os = oss.getManufacturer() + " " + System.getProperty("os.name") + " " + System.getProperty("os.version")
				+ " " + System.getProperty("os.arch");
		String cpu = PlatformDescriptors.func_227775_b_();
		String gpu = gupname;
		String mod = "";

		for (ModInfo mods : ModList.get().getMods()) {
			mod += "[" + ModUtil.getModName(mods.getModId()) + " " + ModUtil.getModVersion(mods.getModId()) + "]" + " ";
		}

		PacketHandler.INSTANCE.sendToServer(new MessageSendSysmtemInfo(java, os, cpu, gpu, mod, "", "", false));
	}

	@SubscribeEvent
	public static void onChatReceived(ClientChatReceivedEvent e) {
		if (e.getType() == ChatType.SYSTEM) {
			if (e.getMessage() instanceof TranslationTextComponent) {
				TranslationTextComponent me = (TranslationTextComponent) e.getMessage();
				if (me.getKey().contains("death") && !me.getKey().contains("rip")) {
					e.setCanceled(true);
				}
			}
			if(e.getMessage().getString().contains("DataList: ")) {
				PassbookItem.oldChatComponent = e.getMessage();
			}
		}
	}

//	@SubscribeEvent
//	public static void onTooltip(ItemTooltipEvent e) {
//
//		e.getToolTip().add(new StringTextComponent(
//				"test=" + Evaluations.getArmorEvaluation(e.getItemStack())));
//
//	}
}
