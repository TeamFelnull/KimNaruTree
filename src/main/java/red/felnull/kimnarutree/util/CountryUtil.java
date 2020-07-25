package red.felnull.kimnarutree.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import red.felnull.kimnarutree.packet.CreateCountryMessage;
import red.felnull.kimnarutree.packet.PacketHandler;

import java.util.UUID;

public class CountryUtil {

    @OnlyIn(Dist.CLIENT)
    public static void sendCreateRequest(String name, byte[] flagImage) {
        String flagUUID = UUID.randomUUID().toString();
        PacketHandler.INSTANCE.sendToServer(new CreateCountryMessage(name, flagUUID));
    }

}
