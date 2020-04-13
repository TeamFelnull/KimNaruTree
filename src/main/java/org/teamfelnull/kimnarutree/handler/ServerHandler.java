package org.teamfelnull.kimnarutree.handler;

import org.teamfelnull.kimnarutree.command.KNTCommands;
import org.teamfelnull.kimnarutree.data.DataLoadSave;
import org.teamfelnull.kimnarutree.money.MoneyConsumptions;
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
		DataLoadSave.serverStarting(ev.getServer());
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent e) {
		DataLoadSave.playerLogin(e.getPlayer());
	}

	@SubscribeEvent
	public static void onPlayerLogout(PlayerLoggedOutEvent e) {
		DataLoadSave.playerLogout(e.getPlayer());
	}

	@SubscribeEvent
	public static void onWorldSave(WorldEvent.Save e) {
		DataLoadSave.worldSave(e.getWorld().getWorld().getServer());
	}

	@SubscribeEvent
	public static void onServerStopping(FMLServerStoppingEvent e) {
		DataLoadSave.serverStopping(e.getServer());
	}

	@SubscribeEvent
	public static void onDeath(LivingDeathEvent e) {

		if (!(e.getEntityLiving() instanceof ServerPlayerEntity))
			return;

		ServerPlayerEntity pl = (ServerPlayerEntity) e.getEntityLiving();
		long mae = MoneyUtil.getPlayerMoney(pl);
		long ato = MoneyConsumptions.consumptionFuneral(MoneyUtil.getPlayerMoney(pl), pl);
		MoneyUtil.setPlayerMoney(pl, ato);

		for (String name : pl.getServer().getOnlinePlayerNames()) {

			ServerPlayerEntity spl = pl.getServer().getPlayerList().getPlayerByUsername(name);

			spl.sendMessage(new TranslationTextComponent("message.rip", e.getSource().getDeathMessage(pl),
					MoneyUtil.getDisplayAmount(mae - MoneyUtil.getPlayerMoney(pl))));

		}
		AdvancementUtil.checkFuneralCost(pl, mae - ato);
	}

}
