package red.felnull.kimnarutree.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.lib.lang.ITranslationEnum;

public class KNTItem extends Item {
    public KNTItem(Properties properties) {
        super(properties);
    }

    public static Item instance(ITranslationEnum klang){
        return instance(klang, 64);
    }

    public static Item instance(ITranslationEnum klang, int maxStackSize){
        return new KNTItem(new Item.Properties().maxStackSize(maxStackSize).group(KNTItemGroup.MOD_TAB)).setRegistryName(KimNaruTree.MOD_ID, klang.getKey());
    }

    public static Item instance(ITranslationEnum klang, EntityType<?> type, int primaryColor, int secondaryColor){
        return new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Properties().group(KNTItemGroup.MOD_TAB)).setRegistryName(KimNaruTree.MOD_ID, klang.getKey());
    }
}
