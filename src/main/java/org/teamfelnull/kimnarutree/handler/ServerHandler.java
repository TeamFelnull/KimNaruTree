package org.teamfelnull.kimnarutree.handler;

import org.teamfelnull.kimnarutree.command.KNTCommands;
import org.teamfelnull.kimnarutree.util.player.PlayerDataLoader;
import org.teamfelnull.kimnarutree.util.player.PlayerDataRegister;
import org.teamfelnull.kimnarutree.util.player.PlayerDatas;
import org.teamfelnull.kimnarutree.util.player.PlayerHelper;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class ServerHandler {
	@SubscribeEvent
	public static void onServerStarting(FMLServerStartingEvent ev) {
		KNTCommands.registerCommand(ev.getCommandDispatcher());
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent e) {
		PlayerDataLoader.readOnlyPlayer(e.getPlayer());
		PlayerDataRegister.fristRegisterPlayerData(e.getPlayer());
	}

	@SubscribeEvent
	public static void onPlayerLogout(PlayerLoggedOutEvent e) {

		if (PlayerDatas.SAVED_PLAYER_DATA.containsKey(PlayerHelper.getUUID(e.getPlayer())))
			PlayerDatas.SAVED_PLAYER_DATA.get(PlayerHelper.getUUID(e.getPlayer())).clear();

		if (PlayerDatas.UNSAVED_PLAYER_DATA.containsKey(PlayerHelper.getUUID(e.getPlayer())))
			PlayerDatas.UNSAVED_PLAYER_DATA.get(PlayerHelper.getUUID(e.getPlayer())).clear();

	}

	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load e) {
		PlayerDataLoader.readerStart(e.getWorld().getWorld().getServer());
	}

	@SubscribeEvent
	public static void onWorldSave(WorldEvent.Save e) {
		PlayerDataLoader.writerStart(e.getWorld().getWorld().getServer());
	}

	@SubscribeEvent
	public static void onWorldUnload(WorldEvent.Unload e) {
		PlayerDatas.SAVED_PLAYER_DATA.clear();
		PlayerDatas.UNSAVED_PLAYER_DATA.clear();

	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
		PlayerDataRegister.updatePlayerData(e.player);
	}
}
