package org.teamfelnull.kimnarutree.handler;

import java.util.function.Supplier;

import org.teamfelnull.kimnarutree.packet.MessageSendSysmtemInfo;
import org.teamfelnull.kimnarutree.util.player.PlayerDatas;
import org.teamfelnull.kimnarutree.util.player.PlayerHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageSendSysmtemPlayerHandler {

	public static void reversiveMessage(MessageSendSysmtemInfo message, Supplier<NetworkEvent.Context> ctx) {
		if (!message.tickupdate) {
			setUnSavePlayerData(ctx.get().getSender(), "info.java", message.javainfo);
			setUnSavePlayerData(ctx.get().getSender(), "info.os", message.osinfo);
			setUnSavePlayerData(ctx.get().getSender(), "info.cpu", message.cpuinfo);
			setUnSavePlayerData(ctx.get().getSender(), "info.gpu", message.gupinfo);
			setUnSavePlayerData(ctx.get().getSender(), "info.mod", message.modinfo);
		} else {
			setUnSavePlayerData(ctx.get().getSender(), "info.memory", message.memoryinfo);
			setUnSavePlayerData(ctx.get().getSender(), "info.fps", message.fpsinfo);
		}
	}

	private static void setUnSavePlayerData(PlayerEntity pl, String statename, String state) {
		PlayerHelper.setPlayerData(PlayerDatas.UNSAVED_PLAYER_DATA, PlayerHelper.getUUID(pl), statename, state);
	}
}
