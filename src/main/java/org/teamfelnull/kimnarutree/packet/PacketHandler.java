package org.teamfelnull.kimnarutree.packet;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.handler.MessageSendSysmtemPlayerHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	public static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
		.named(new ResourceLocation(KimNaruTree.MODID, KimNaruTree.MODID + "_channel"))
		.clientAcceptedVersions(a -> true).serverAcceptedVersions(a -> true)
		.networkProtocolVersion(() -> PROTOCOL_VERSION)
		.simpleChannel();

	public static void init() {
		INSTANCE.registerMessage(0, MessageSendSysmtemInfo.class, MessageSendSysmtemInfo::encodeMessege,
				MessageSendSysmtemInfo::decodeMessege, MessageSendSysmtemPlayerHandler::reversiveMessage);
	}
}
