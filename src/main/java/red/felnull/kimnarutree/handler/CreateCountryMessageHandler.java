package red.felnull.kimnarutree.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.network.NetworkEvent;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.lib.MESSAGE;
import red.felnull.kimnarutree.lib.StringUtil;
import red.felnull.kimnarutree.packet.CreateCountryMessage;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.util.function.Supplier;

import static red.felnull.kimnarutree.lib.TranslationUtil.kntTranslate;

public class CreateCountryMessageHandler {


    public static void reversiveMessage(CreateCountryMessage message, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity pl = ctx.get().getSender();

        if (Country.getCountryByPlayer(pl) != null) {
            pl.sendStatusMessage(kntTranslate(MESSAGE.COUNTRY_BELONG_TO), false);
            return;
        }

        Country.register(pl, message);
        pl.getServer().getPlayerList().getPlayers().forEach( player ->
            player.sendStatusMessage(kntTranslate(MESSAGE.COUNTRY_NOTIFICATION
                    , StringUtil.colorText(PlayerHelper.getUserName(player), TextFormatting.GREEN)
                    , StringUtil.colorText(message.name, TextFormatting.YELLOW)), false));
    }
}
