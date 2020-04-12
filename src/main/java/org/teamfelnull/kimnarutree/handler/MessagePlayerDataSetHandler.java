package org.teamfelnull.kimnarutree.handler;

import java.util.function.Supplier;

import org.teamfelnull.kimnarutree.data.SavedPlayerData;
import org.teamfelnull.kimnarutree.packet.MessagePlayerDataSet;

import net.minecraftforge.fml.network.NetworkEvent;

public class MessagePlayerDataSetHandler {

	public static void reversiveMessage(MessagePlayerDataSet message, Supplier<NetworkEvent.Context> ctx) {
		SavedPlayerData.setData(message.playername, message.statename, message.state);
	}
}
