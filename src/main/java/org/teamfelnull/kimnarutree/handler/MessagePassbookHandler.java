package org.teamfelnull.kimnarutree.handler;

import java.util.function.Supplier;

import org.teamfelnull.kimnarutree.packet.MessagePassbook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessagePassbookHandler {
	public static ITextComponent newChatComponent;
	public static ITextComponent oldChatComponent;
	private static final int DELETION_ID = 19194545;

	public static void reversiveMessage(MessagePassbook message, Supplier<NetworkEvent.Context> ctx) {
		newChatComponent = new StringTextComponent("DataList: " + message.bankdata + " " + message.playerdata);
		@SuppressWarnings("resource")
		NewChatGui chatGui = Minecraft.getInstance().ingameGUI.getChatGUI();
		if (oldChatComponent != null) {
			chatGui.deleteChatLine(DELETION_ID);
		}
		chatGui.printChatMessageWithOptionalDeletion(newChatComponent, DELETION_ID);
	}
}
