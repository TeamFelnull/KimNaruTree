package org.teamfelnull.kimnarutree.item;

import org.teamfelnull.kimnarutree.KimNaruTree;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class KNTItems {
	//名前
	public static Item TEST_ITEM = newItem("test_item");
	public static Item PICKY = newItem("picky");

	public static void registerItem(IForgeRegistry<Item> r) {
		registryItem(r, TEST_ITEM);
		registryItem(r, PICKY);
	}

	private static Item newItem(String name) {
		return new Item(new Item.Properties().group(KNTItemGroup.MOD_TAB))
				.setRegistryName(KimNaruTree.MODID, name);
	}

	private static void registryItem(IForgeRegistry<Item> r, Item i) {
		KimNaruTree.LOGGER.info("Registering Item : " + i.getRegistryName());
		r.register(i);
	}

}
