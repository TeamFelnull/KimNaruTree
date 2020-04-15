package org.teamfelnull.kimnarutree.item;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.money.BankData;
import org.teamfelnull.kimnarutree.money.PlayerData;
import org.teamfelnull.kimnarutree.packet.MessagePassbook;
import org.teamfelnull.kimnarutree.packet.PacketHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;

public class PassbookItem extends Item {

	public PassbookItem(Properties properties) {
		super(properties);
	}

	public static Item newPassbook() {
		return new PassbookItem(new Item.Properties().group(KNTItemGroup.MOD_TAB))
				.setRegistryName(KimNaruTree.MODID, "passbook");
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity pl, Hand hand) {

		if (!world.isRemote) {

			PacketHandler.INSTANCE.sendTo(new MessagePassbook(BankData.getAll(), PlayerData.getAll(pl)),
					((ServerPlayerEntity) pl).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);

		}
		return ActionResult.func_226248_a_(pl.getHeldItem(hand));
	}
}
