package red.felnull.kimnarutree.packet;

import net.minecraft.network.PacketBuffer;

public class CreateCountryMessage {
    public String name;
    public String flagUUID;

    public CreateCountryMessage(String name, String flagUUID) {
        this.name = name;
        this.flagUUID = flagUUID;
    }

    public static CreateCountryMessage decodeMessege(PacketBuffer buffer) {
        return new CreateCountryMessage(buffer.readString(32767), buffer.readString(32767));
    }

    public static void encodeMessege(CreateCountryMessage messegeIn, PacketBuffer buffer) {
        buffer.writeString(messegeIn.name);
        buffer.writeString(messegeIn.flagUUID);
    }
}
