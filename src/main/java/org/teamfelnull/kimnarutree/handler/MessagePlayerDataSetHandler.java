package org.teamfelnull.kimnarutree.handler;

import java.util.function.Supplier;

import org.teamfelnull.kimnarutree.packet.MessagePlayerDataSet;
import org.teamfelnull.kimnarutree.util.player.PlayerDataLoader;

import net.minecraftforge.fml.network.NetworkEvent;

public class MessagePlayerDataSetHandler {

	public static void reversiveMessage(MessagePlayerDataSet message, Supplier<NetworkEvent.Context> ctx) {
		PlayerDataLoader.setPlayerData(message.playername, message.statename, message.state);
	}
}
