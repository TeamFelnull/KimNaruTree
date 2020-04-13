package org.teamfelnull.kimnarutree.item;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.money.BankData;
import org.teamfelnull.kimnarutree.money.PlayerData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class PassbookItem extends Item {

	public PassbookItem(Properties properties) {
		super(properties);
	}

	public static Item newPassbook() {
		return new PassbookItem(new Item.Properties().group(KNTItemGroup.MOD_TAB))
				.setRegistryName(KimNaruTree.MODID, "passbook");
	}
	public static ITextComponent newChatComponent;
	public static ITextComponent oldChatComponent;
	private static final int DELETION_ID = 19194545;

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity pl, Hand hand) {

		if(!world.isRemote) {
			newChatComponent = new StringTextComponent("DataList: " + BankData.getAll() + " " + PlayerData.getAll(pl));
			@SuppressWarnings("resource")
			NewChatGui chatGui = Minecraft.getInstance().ingameGUI.getChatGUI();
			if(oldChatComponent != null) {
				chatGui.deleteChatLine(DELETION_ID);
			}
			chatGui.printChatMessageWithOptionalDeletion(newChatComponent, DELETION_ID);
		}
		return ActionResult.func_226248_a_(pl.getHeldItem(hand));
	}
}
