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

    public static CreateCountryMessage decodeMessege(PacketBuffer buffer) {
        return new CreateCountryMessage(buffer.readString(32767), buffer.readString(32767), buffer.readInt(), buffer.readInt());
    }

    public static void encodeMessege(CreateCountryMessage messegeIn, PacketBuffer buffer) {
        buffer.writeString(messegeIn.name);
        buffer.writeString(messegeIn.flagUUID);
        buffer.writeInt(messegeIn.flagW);
        buffer.writeInt(messegeIn.flagH);
    }
}
