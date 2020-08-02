package red.felnull.kimnarutree.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import red.felnull.kimnarutree.command.KNTCommands;
import red.felnull.kimnarutree.country.Country;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.money.MoneyConsumptions;
import red.felnull.kimnarutree.util.MoneyUtil;
import red.felnull.otyacraftengine.api.ResponseSender;
import red.felnull.otyacraftengine.util.ServerHelper;

public class ServerHandler {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent e) {
        KNTCommands.registerCommand(e.getDispatcher());
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent e) {
        if (!(e.getEntityLiving() instanceof ServerPlayerEntity)) {
            return;
        }
        ServerPlayerEntity pl = (ServerPlayerEntity) e.getEntityLiving();
        long mae = MoneyUtil.getMoney(pl, false);
        long ato = MoneyConsumptions.ofFuneral(mae);
        long sabun = mae - ato;
        MoneyUtil.setMoney(pl, ato);
        for (ServerPlayerEntity spl : pl.getServer().getPlayerList().getPlayers()) {
            spl.sendStatusMessage(new TranslationTextComponent("message.rip", e.getSource().getDeathMessage(pl), MoneyUtil.getDisplayAmount(sabun)), false);
        }
        MoneyUtil.checkFuneralCost(pl, sabun);
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent e) {
        ServerHelper.getOnlinePlayers().forEach(n -> {
            Country country = Country.getContryByTerritory(n.world.func_234923_W_().func_240901_a_(), new ChunkPos(n.func_233580_cy_()));
            if (country != null) {
                ResponseSender.sendToClient((ServerPlayerEntity) n, KNTDatas.COUNTRY_SYNC, 0, country.getUuid(), country.write(new CompoundNBT()));
            } else {
                ResponseSender.sendToClient((ServerPlayerEntity) n, KNTDatas.COUNTRY_SYNC, 1, "", new CompoundNBT());
            }
        });
    }
}
