package org.teamfelnull.kimnarutree.item;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.entity.KNTEntitys;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.registries.IForgeRegistry;

public class KNTItems {

	//名前
	public static Item TEST_ITEM = newItem("test_item");
	public static Item PICKY = newItem("picky");
	public static Item PICKYDED = newItem("pickyded");

	//Entity, 名前, 色1, 色2
	public static Item NPC_SPAWN_EGG = newSpawnEgg(KNTEntitys.NPC, "npc_spawn_egg", 10905133, 4203016);

	private static Item newItem(String name) {
		return new Item(new Item.Properties().group(KNTItemGroup.MOD_TAB))
				.setRegistryName(KimNaruTree.MODID, name);
	}

	private static Item newSpawnEgg(EntityType<?> type, String name,int color1, int color2) {
		return new SpawnEggItem(type, color1, color2,(new Item.Properties())
				.group(KNTItemGroup.MOD_TAB)).setRegistryName(KimNaruTree.MODID, name);
	}

	public static void registerItem(IForgeRegistry<Item> r) {
		registryItem(r, TEST_ITEM);
		registryItem(r, PICKY);
		registryItem(r, PICKYDED);
		registryItem(r, NPC_SPAWN_EGG);
	}

	private static void registryItem(IForgeRegistry<Item> r, Item i) {
		KimNaruTree.LOGGER.info("Registering Item : " + i.getRegistryName());
		r.register(i);
	}
}
