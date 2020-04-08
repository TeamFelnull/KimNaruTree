package org.teamfelnull.kimnarutree.item;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.entity.KNTEntitys;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.registries.IForgeRegistry;

public class KNTItems {
	//名前
	public static Item TEST_ITEM = newItem("test_item");
	public static Item PICKY = newItem("picky");

	public static Item NPC_SPAWN_EGG = new SpawnEggItem(KNTEntitys.NPC, 10905133, 4203016,
			(new Item.Properties()).group(KNTItemGroup.MOD_TAB)).setRegistryName(KimNaruTree.MODID, "npc_spawn_egg");

	public static void registerItem(IForgeRegistry<Item> r) {
		registryItem(r, TEST_ITEM);
		registryItem(r, PICKY);
		registryItem(r, NPC_SPAWN_EGG);
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
