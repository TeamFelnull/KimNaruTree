package red.felnull.kimnarutree.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.packet.CreateCountryMessage;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.util.UUID;
import java.util.function.Supplier;

public class CreateCountryMessageHandler {


    public static void reversiveMessage(CreateCountryMessage message, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity pl = ctx.get().getSender();

        if (Country.getCountryByPlayer(pl) != null) {
            pl.sendStatusMessage(new TranslationTextComponent("country.belongTo"), false);
            return;
        }

        Country.register(pl, message);
        pl.getServer().getPlayerList().getPlayers().forEach( player ->
            player.sendStatusMessage(new TranslationTextComponent("country.notification"
                    , new StringTextComponent(PlayerHelper.getUserName(player)).func_240699_a_(TextFormatting.GREEN)
                    , new StringTextComponent(message.name).func_240699_a_(TextFormatting.YELLOW)), false));
    }
}
