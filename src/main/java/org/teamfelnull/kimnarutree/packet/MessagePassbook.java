package org.teamfelnull.kimnarutree.packet;

import net.minecraft.network.PacketBuffer;

public class MessagePassbook {
	public String bankdata;
	public String playerdata;

	public MessagePassbook(String BankData, String PlayerData) {
		this.bankdata = BankData;
		this.playerdata = PlayerData;
	}

	public static MessagePassbook decodeMessege(PacketBuffer buffer) {
		return new MessagePassbook(buffer.readString(32767), buffer.readString(32767));
	}

	public static void encodeMessege(MessagePassbook messegeIn, PacketBuffer buffer) {
		buffer.writeString(messegeIn.bankdata);
		buffer.writeString(messegeIn.playerdata);
	}
}
