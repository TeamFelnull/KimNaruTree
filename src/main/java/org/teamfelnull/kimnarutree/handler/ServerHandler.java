package org.teamfelnull.kimnarutree.handler;

import org.teamfelnull.kimnarutree.command.KNTCommands;
import org.teamfelnull.kimnarutree.data.DataReader;
import org.teamfelnull.kimnarutree.data.DataWriter;
import org.teamfelnull.kimnarutree.data.KNTDatas;
import org.teamfelnull.kimnarutree.money.MoneyConsumptions;
import org.teamfelnull.kimnarutree.money.PlayerData;
import org.teamfelnull.kimnarutree.util.AdvancementUtil;
import org.teamfelnull.kimnarutree.util.MoneyUtil;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

public class ServerHandler {

	@SubscribeEvent
	public static void onServerStarting(FMLServerStartingEvent ev) {
		KNTCommands.registerCommand(ev.getCommandDispatcher());
		DataReader.onServerStarting(ev.getServer());
	}

	@SubscribeEvent
	public static void onWorldSave(WorldEvent.Save e) {
		DataWriter.onWorldSave(e.getWorld().getWorld().getServer());
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent e) {
		DataReader.onPlayerLogin(e.getPlayer());
	}

	@SubscribeEvent
	public static void onPlayerLogout(PlayerLoggedOutEvent e) {
		DataWriter.onPlayerLogout(e.getPlayer());
	}

	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load e) {
		DataReader.onWorldLoad(e.getWorld().getWorld().getServer());
	}

	@SubscribeEvent
	public static void onServerStopping(FMLServerStoppingEvent e) {
		KNTDatas.SAVED_PLAYER_DATA.clear();
		KNTDatas.UNSAVED_PLAYER_DATA.clear();
		KNTDatas.BASE_ITEM_WORTH_DATA.clear();
	}

	@SubscribeEvent
	public static void onDeath(LivingDeathEvent e) {
		if (!(e.getEntityLiving() instanceof ServerPlayerEntity)) {
			return;
		}
		ServerPlayerEntity pl = (ServerPlayerEntity) e.getEntityLiving();
		long mae = PlayerData.getMoney(pl);
		long ato = MoneyConsumptions.ofFuneral(mae);
		long sabun = mae - ato;
		PlayerData.setMoney(pl, ato);
		for (ServerPlayerEntity spl : pl.getServer().getPlayerList().getPlayers()) {
			spl.sendMessage(new TranslationTextComponent("message.rip", e.getSource().getDeathMessage(pl),
					MoneyUtil.getDisplayAmount(sabun)));
		}
		AdvancementUtil.checkFuneralCost(pl, sabun);
	}

}
