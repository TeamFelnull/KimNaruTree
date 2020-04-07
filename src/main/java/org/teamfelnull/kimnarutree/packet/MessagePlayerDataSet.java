package org.teamfelnull.kimnarutree.packet;

import net.minecraft.network.PacketBuffer;

public class MessagePlayerDataSet {
	public String playername;
	public String statename;
	public String state;

	public MessagePlayerDataSet(String Playername, String Statename, String State) {
		this.playername = Playername;
		this.statename = Statename;
		this.state = State;
	}

	public static void encodeMessege(MessagePlayerDataSet messegeIn, PacketBuffer buffer) {
		buffer.writeString(messegeIn.playername);
		buffer.writeString(messegeIn.statename);
		buffer.writeString(messegeIn.state);

	}

	public static MessagePlayerDataSet decodeMessege(PacketBuffer buffer) {

		return new MessagePlayerDataSet(buffer.readString(), buffer.readString(), buffer.readString());
	}

}
