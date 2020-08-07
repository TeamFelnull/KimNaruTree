package red.felnull.kimnarutree.packet;

import net.minecraft.network.PacketBuffer;

public class CreateCountryMessage {
    public String name;
    public String flagUUID;
    public int flagW;
    public int flagH;

    public CreateCountryMessage(String name, String flagUUID, int flagW, int flagH) {
        this.name = name;
        this.flagUUID = flagUUID;
        this.flagW = flagW;
        this.flagH = flagH;
    }

    public static CreateCountryMessage decodeMessage(PacketBuffer buffer) {
        return new CreateCountryMessage(buffer.readString(32767), buffer.readString(32767), buffer.readInt(), buffer.readInt());
    }

    public static void encodeMessage(CreateCountryMessage messageIn, PacketBuffer buffer) {
        buffer.writeString(messageIn.name);
        buffer.writeString(messageIn.flagUUID);
        buffer.writeInt(messageIn.flagW);
        buffer.writeInt(messageIn.flagH);
    }
}
