package red.felnull.kimnarutree.packet;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.handler.CreateCountryMessageHandler;

public class PacketHandler {
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(KimNaruTree.MODID, "knt_channel")).clientAcceptedVersions(a -> true).serverAcceptedVersions(a -> true).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();
    private static int integer = -1;

    private static int next() {
        integer++;
        return integer;
    }

    public static void init() {
        INSTANCE.registerMessage(next(), CreateCountryMessage.class, CreateCountryMessage::encodeMessage, CreateCountryMessage::decodeMessage, CreateCountryMessageHandler::reversiveMessage);
    }

}
