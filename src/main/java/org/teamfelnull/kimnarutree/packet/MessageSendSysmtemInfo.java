package org.teamfelnull.kimnarutree.packet;

import net.minecraft.network.PacketBuffer;

public class MessageSendSysmtemInfo {

	public String javainfo;
	public String osinfo;
	public String cpuinfo;
	public String gupinfo;
	public String modinfo;
	public String memoryinfo;
	public String fpsinfo;
	public boolean tickupdate;

	public static MessageSendSysmtemInfo decodeMessege(PacketBuffer buffer) {
		return new MessageSendSysmtemInfo(buffer.readString(32767), buffer.readString(32767),
				buffer.readString(32767),buffer.readString(32767), buffer.readString(32767),
				buffer.readString(32767), buffer.readString(32767),buffer.readBoolean());
	}

	public MessageSendSysmtemInfo(String javaInfo, String osInfo, String cpuInfo, String gupInfo,
			String modInfo,String memoryInfo,String fpsInfo, boolean tickUpdate) {

		this.javainfo = javaInfo;
		this.osinfo = osInfo;
		this.cpuinfo = cpuInfo;
		this.gupinfo = gupInfo;
		this.modinfo = modInfo;
		this.memoryinfo = memoryInfo;
		this.fpsinfo = fpsInfo;
		this.tickupdate = tickUpdate;
	}

	public static void encodeMessege(MessageSendSysmtemInfo messegeIn, PacketBuffer buffer) {
		buffer.writeString(messegeIn.javainfo);
		buffer.writeString(messegeIn.osinfo);
		buffer.writeString(messegeIn.cpuinfo);
		buffer.writeString(messegeIn.gupinfo);
		buffer.writeString(messegeIn.modinfo);
		buffer.writeString(messegeIn.memoryinfo);
		buffer.writeString(messegeIn.fpsinfo);
		buffer.writeBoolean(messegeIn.tickupdate);
	}
}
