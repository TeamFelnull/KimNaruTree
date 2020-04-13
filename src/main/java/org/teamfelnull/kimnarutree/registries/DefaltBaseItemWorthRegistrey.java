package org.teamfelnull.kimnarutree.registries;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class DefaltBaseItemWorthRegistrey {
	public static void init() {
		registreDefaltBaseItemWorth(Items.AIR, 0);
		registreDefaltBaseItemWorth(Items.STONE, 100);
		registreDefaltBaseItemWorth(Items.GRANITE, 110);
		registreDefaltBaseItemWorth(Items.POLISHED_GRANITE, 50);
		registreDefaltBaseItemWorth(Items.DIORITE, 10);
		registreDefaltBaseItemWorth(Items.POLISHED_DIORITE, 20);
		registreDefaltBaseItemWorth(Items.ANDESITE, 114);
		registreDefaltBaseItemWorth(Items.POLISHED_ANDESITE, 514);

	}

	public static void registreDefaltBaseItemWorth(Item item, long worth) {
		KNTRegistries.DEFALT_BASE_ITEM_WORTH.put(item, worth);
	}

	@SuppressWarnings("deprecation")
	public static void registreDefaltBaseItemWorth(Block block, long worth) {
		KNTRegistries.DEFALT_BASE_ITEM_WORTH.put(Item.getItemFromBlock(block), worth);
	}
}
