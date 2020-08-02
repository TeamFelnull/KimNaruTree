package red.felnull.kimnarutree.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.entity.KNTEntityTypes;

public class KNTItems {

    //名前
    public static final Item TEST_ITEM = newItem("test_item");
    public static final Item PICKY = newItem("picky");
    public static final Item PICKYDED = newItem("pickyded");
//	public static Item PASSBOOK = PassbookItem.newPassbook();

    public static final Item COUNTRY_DEBUG_STICK = new CountryDebugStickItem(new Item.Properties().maxStackSize(1).group(KNTItemGroup.MOD_TAB)).setRegistryName(new ResourceLocation(KimNaruTree.MODID, "country_debug_stick"));

    public static final Item NATIONAL_FLAG = new NationalFlagItem(new Item.Properties().group(KNTItemGroup.MOD_TAB)).setRegistryName(new ResourceLocation(KimNaruTree.MODID, "national_flag"));

    //Entity, 名前, 色1, 色2
    public static Item NPC_SPAWN_EGG = newSpawnEgg(KNTEntityTypes.NPC, "npc_spawn_egg", 10905133, 4203016);

    private static Item newItem(String name) {
        return new Item(new Item.Properties().group(KNTItemGroup.MOD_TAB))
                .setRegistryName(KimNaruTree.MODID, name);
    }

    private static Item newSpawnEgg(EntityType<?> type, String name, int color1, int color2) {
        return new SpawnEggItem(type, color1, color2, (new Item.Properties())
                .group(KNTItemGroup.MOD_TAB)).setRegistryName(KimNaruTree.MODID, name);
    }

    public static void registerItem(IForgeRegistry<Item> r) {
        registryItem(r, TEST_ITEM);
        registryItem(r, PICKY);
        registryItem(r, PICKYDED);
        //	registryItem(r, PASSBOOK);
        registryItem(r, NPC_SPAWN_EGG);
        registryItem(r, NATIONAL_FLAG);
        registryItem(r, COUNTRY_DEBUG_STICK);
    }

    private static void registryItem(IForgeRegistry<Item> r, Item i) {
        KimNaruTree.LOGGER.info("Registering Item : " + i.getRegistryName());
        r.register(i);
    }
}
