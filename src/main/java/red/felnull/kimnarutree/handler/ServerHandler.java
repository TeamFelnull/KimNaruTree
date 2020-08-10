package red.felnull.kimnarutree.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import red.felnull.kimnarutree.command.KNTCommands;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.data.bank.CentralBank;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.data.player.KNTPlayerData;
import red.felnull.kimnarutree.data.territory.Territory;
import red.felnull.kimnarutree.lib.lang.MESSAGE;
import red.felnull.kimnarutree.lib.lang.TranslationUtil;
import red.felnull.kimnarutree.util.MoneyUtil;
import red.felnull.otyacraftengine.api.ResponseSender;
import red.felnull.otyacraftengine.util.PlayerHelper;
import red.felnull.otyacraftengine.util.ServerHelper;

public class ServerHandler {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent e) {
        KNTCommands.registerCommand(e.getDispatcher());
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent e) {
        if (!(e.getEntityLiving() instanceof ServerPlayerEntity))
            return;

        ServerPlayerEntity pl = (ServerPlayerEntity) e.getEntityLiving();
        KNTPlayerData data = new KNTPlayerData(pl);
        long mae = data.getMoney();
        long ato = data.funeralCost();
        long sabun = mae - ato;
        data.setMoney(sabun);
        pl.getServer().getPlayerList().getPlayers().forEach( spl ->
            spl.sendStatusMessage(TranslationUtil.kntTranslate(MESSAGE.RIP, e.getSource().getDeathMessage(pl), MoneyUtil.getDisplayAmount(sabun)), false));
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent e) {
        ServerHelper.getOnlinePlayers().forEach(pl -> {
            Country country = Country.getCountryOn(pl);
            if (country != null) {
                ResponseSender.sendToClient((ServerPlayerEntity) pl, KNTDatas.COUNTRY_SYNC, 0, country.getUUID(), country.getNBT());
            } else {
                ResponseSender.sendToClient((ServerPlayerEntity) pl, KNTDatas.COUNTRY_SYNC, 1, "", new CompoundNBT());
            }
        });
        //CentralBank.IKSGFund();
    }

    @SubscribeEvent
    public static void onPlayerLogIn(PlayerEvent.PlayerLoggedInEvent e) {
        if(!Knbt.PLAYER_DATA.contains(PlayerHelper.getUUID(e.getPlayer())))
            KNTPlayerData.register(e.getPlayer());
    }

    @SubscribeEvent
    public static void onServerStart(FMLServerStartingEvent e) {
        if(!Knbt.Bank().getNBTs().contains(CentralBank.CENTRAL_BANK_UUID))
            CentralBank.register();
    }

    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save e) {
        if(!Knbt.Territory().getNBTs().contains(e.getWorld().getWorld().func_234923_W_().func_240901_a_().toString()))
        Territory.register(e.getWorld().getWorld());
    }
}
