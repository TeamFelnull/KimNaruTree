package red.felnull.kimnarutree.item;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.entity.KNTEntityTypes;
import red.felnull.kimnarutree.lib.lang.ITEM;

public class KNTItems {

    public static final String REGISTERING_ITEM = "Registering Item : ";

    public static final Item TEST_ITEM = KNTItem.instance(ITEM.TEST_ITEM);
    public static final Item PICKY = KNTItem.instance(ITEM.PICKY);
    public static final Item PICKY_DED = KNTItem.instance(ITEM.PICKY_DED);
    public static Item NPC_SPAWN_EGG = KNTItem.instance(ITEM.NPC_SPAWN_EGG, KNTEntityTypes.NPC, 10905133, 4203016);
    public static Item PASSBOOK = PassbookItem.instance(ITEM.PASSBOOK, 1);
    public static final Item NATIONAL_FLAG = NationalFlagItem.instance(ITEM.NATIONAL_FLAG);
    public static final Item COUNTRY_DEBUG_STICK = CountryDebugStickItem.instance(ITEM.COUNTRY_DEBUG_STICK, 1);

    public static void registerItem(IForgeRegistry<Item> r) {
        registryItem(r, TEST_ITEM);
        registryItem(r, PICKY);
        registryItem(r, PICKY_DED);
        registryItem(r, NPC_SPAWN_EGG);
        registryItem(r, PASSBOOK);
        //	registryItem(r, PASSBOOK);
        registryItem(r, NATIONAL_FLAG);
        registryItem(r, COUNTRY_DEBUG_STICK);
    }

    private static void registryItem(IForgeRegistry<Item> r, Item i) {
        KimNaruTree.LOGGER.info(REGISTERING_ITEM + i.getRegistryName());
        r.register(i);
    }
}
