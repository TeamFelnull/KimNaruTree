package red.felnull.kimnarutree.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.packet.CreateCountryMessage;
import red.felnull.kimnarutree.packet.PacketHandler;
import red.felnull.otyacraftengine.api.DataSendReceiverManager;

import java.util.UUID;

public class CountryUtil {

    @OnlyIn(Dist.CLIENT)
    public static void sendCreateRequest(String name, byte[] flagImage) {
        String flagID = UUID.randomUUID().toString();
        DataSendReceiverManager.instance().sendToServer(KNTDatas.WORLD_NATIONAL_FLAG, flagID, flagImage);
        PacketHandler.INSTANCE.sendToServer(new CreateCountryMessage(name, flagID));
    }

}
