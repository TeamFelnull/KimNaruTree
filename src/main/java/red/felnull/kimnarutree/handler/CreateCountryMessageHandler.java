package red.felnull.kimnarutree.handler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.country.Country;
import red.felnull.kimnarutree.packet.CreateCountryMessage;
import red.felnull.otyacraftengine.data.WorldDataManager;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.util.UUID;
import java.util.function.Supplier;

public class CreateCountryMessageHandler {


    public static void reversiveMessage(CreateCountryMessage message, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity player = ctx.get().getSender();
        String countryUUID = UUID.randomUUID().toString();
        Country country = new Country(countryUUID);
        country.setName(message.name);
        country.setFoundedPlayer(PlayerHelper.getUserName(player), PlayerHelper.getUUID(player));
        country.setFlagImageUUID(message.flagUUID);

        WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).put(countryUUID, country.write(new CompoundNBT()));


        for (String playerd : ctx.get().getSender().getServer().getPlayerList().getOnlinePlayerNames()) {
            ServerPlayerEntity onsp = ctx.get().getSender().getServer().getPlayerList().getPlayerByUsername(playerd);
            onsp.sendStatusMessage(new TranslationTextComponent("country.notification", new StringTextComponent(PlayerHelper.getUserName(player)).func_240699_a_(TextFormatting.GREEN), new StringTextComponent(message.name).func_240699_a_(TextFormatting.YELLOW)), false);
        }


    }
}
