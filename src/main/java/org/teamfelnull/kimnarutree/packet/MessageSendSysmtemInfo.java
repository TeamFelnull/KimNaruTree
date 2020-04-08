package org.teamfelnull.kimnarutree.packet;

import java.util.List;

import net.minecraft.network.PacketBuffer;

public class MessageSendSysmtemInfo {
	public String javainfo;
	public String osinfo;
	public String cpuinfo;
	public String gupinfo;
	public String memoryinfo;
	public String fpsinfo;
	public boolean tickupdate;
	public List<Integer> cpuu;

	public MessageSendSysmtemInfo(String javaInfo, String osInfo, String cpuInfo, String gupInfo, String memoryInfo,
			String fpsInfo, boolean tickUpdate) {
		this.javainfo = javaInfo;
		this.osinfo = osInfo;
		this.cpuinfo = cpuInfo;
		this.gupinfo = gupInfo;
		this.memoryinfo = memoryInfo;
		this.fpsinfo = fpsInfo;
		this.tickupdate = tickUpdate;
	}

	public static void encodeMessege(MessageSendSysmtemInfo messegeIn, PacketBuffer buffer) {
		buffer.writeString(messegeIn.javainfo);
		buffer.writeString(messegeIn.osinfo);
		buffer.writeString(messegeIn.cpuinfo);
		buffer.writeString(messegeIn.gupinfo);
		buffer.writeString(messegeIn.memoryinfo);
		buffer.writeString(messegeIn.fpsinfo);
		buffer.writeBoolean(messegeIn.tickupdate);
	}

	public static MessageSendSysmtemInfo decodeMessege(PacketBuffer buffer) {

		return new MessageSendSysmtemInfo(buffer.readString(), buffer.readString(), buffer.readString(),
				buffer.readString(), buffer.readString(), buffer.readString(), buffer.readBoolean());
	}
}