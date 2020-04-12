package org.teamfelnull.kimnarutree.item;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.money.BankData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class Passbook extends Item {

	public Passbook(Properties properties) {
		super(properties);
	}

	public static Item newPassbook() {
		return new Passbook(new Item.Properties().group(KNTItemGroup.MOD_TAB))
				.setRegistryName(KimNaruTree.MODID, "passbook");
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		player.sendMessage(new StringTextComponent(BankData.getAll()));
		return ActionResult.func_226250_c_(player.getHeldItem(hand));
	}

}
