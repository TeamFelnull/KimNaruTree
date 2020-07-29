package red.felnull.kimnarutree.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import red.felnull.kimnarutree.command.KNTCommands;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.money.MoneyConsumptions;
import red.felnull.kimnarutree.util.MoneyUtil;
import red.felnull.otyacraftengine.api.event.ReceiverEvent;

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
    public static void onReceive(ReceiverEvent.Server.Pos e) {
        if (e.getLocation().equals(KNTDatas.WORLD_NATIONAL_FLAG)) {
            System.out.println(e.getName());
        }
    }


}
